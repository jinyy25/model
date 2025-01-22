package com.practice.cycle.model.repository.custom;

import com.practice.cycle.model.dto.ModelRequestDto;
import com.practice.cycle.model.dto.ModelResponseDto;
import com.practice.cycle.model.dto.QModelResponseDto;
import com.querydsl.core.BooleanBuilder;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import static com.practice.cycle.model.entity.QModel.model;
import static com.practice.cycle.model.entity.QLuxury.luxury;
import static com.practice.cycle.model.entity.QModelLuxury.modelLuxury;


import java.util.List;


@RequiredArgsConstructor
public class ModelRepositoryCustomImpl implements ModelRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    // 공통반환
    private List<ModelResponseDto> getModelsWithLuxury(BooleanBuilder builder) {
        return queryFactory.select(
                new QModelResponseDto(
                        model.id,
                        model.name,
                        model.gender,
                        Expressions.stringTemplate("GROUP_CONCAT({0})", luxury.name).as("luxuries")))
                .from(model)
                .innerJoin(model.modelLuxuries, modelLuxury) // 모델과 모델-럭셔리 중간 테이블 조인
                .innerJoin(luxury).on(modelLuxury.luxury.eq(luxury)) // 모델-럭셔리와 럭셔리 테이블을 조인
                .where(builder)
                .groupBy(model.id, model.name, model.gender)
                .fetch();
    }

    //1. 조회
    public List<ModelResponseDto> findModelsWithLuxury(ModelRequestDto modelRequestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(modelRequestDto.getName())) {
            builder.and(model.name.eq(modelRequestDto.getName()));
        }

        if (modelRequestDto.getGender() != null) {
            builder.and(model.gender.eq(modelRequestDto.getGender()));
        }

        if (modelRequestDto.getLuxuryId() != null) {
            builder.and(modelLuxury.luxury.id.eq(modelRequestDto.getLuxuryId()));
        }

        return  getModelsWithLuxury(builder);
    }


    // 저장
    public ModelResponseDto saveModelsWithLuxury(ModelRequestDto modelRequestDto){
        // Step 1: Model 엔티티 저장
        queryFactory.insert(model)
                .columns(model.name, model.gender)
                .values(modelRequestDto.getName(), modelRequestDto.getGender())
                .execute();

        // Step 2: 저장된 Model 엔티티를 가져오기 (ID 활용)
        Long modelId = queryFactory.select(model.id)
                .from(model)
                .where(model.name.eq(modelRequestDto.getName()), model.gender.eq(modelRequestDto.getGender()))
                .fetchOne();

        if (modelId == null) {
            throw new IllegalStateException("Model 저장 중 문제가 발생했습니다.");
        }

        // Step 3: ModelLuxury 엔티티 리스트 생성 및 저장
        modelRequestDto.getLuxuryIds().forEach(luxuryId -> {
            queryFactory.insert(modelLuxury)
                    .columns(modelLuxury.luxury, modelLuxury.model)
                    .values(
                            queryFactory.select(luxury).from(luxury).where(luxury.id.eq(luxuryId)).fetchOne(),
                            queryFactory.select(model).from(model).where(model.id.eq(modelId)).fetchOne()
                    )
                    .execute();
        });

        // Step 4: 저장된 Model과 관련된 luxury들을 조회하여 반환 (공통 조회 로직 호출)
        List<ModelResponseDto> response = getModelsWithLuxury(new BooleanBuilder().and(model.id.eq(modelId)));

        return response.isEmpty() ? null : response.get(0); // 단일 결과 반환
    }

    //업데이트
    public ModelResponseDto updateModelsWithLuxury (ModelRequestDto modelRequestDto){
        // Step 1: 모델 업데이트 (name, gender)
        long updatedCount = queryFactory.update(model)
                .set(model.name, modelRequestDto.getName())
                .set(model.gender, modelRequestDto.getGender())
                .where(model.id.eq(modelRequestDto.getId())) // 전달된 ID를 기준으로 업데이트
                .execute();

        if (updatedCount == 0) {
            throw new IllegalStateException("Model 업데이트 중 문제가 발생했습니다.");
        }


        // Step 2: 기존 modelLuxury 삭제 (연관된 럭셔리 정보를 삭제)
        queryFactory.delete(modelLuxury)
                .where(modelLuxury.model.id.eq(modelRequestDto.getId()))
                .execute();

        // Step 3: 새로 전달된 luxuryIds를 기반으로 modelLuxury 추가
        modelRequestDto.getLuxuryIds().forEach(luxuryId -> {
            queryFactory.insert(modelLuxury)
                    .columns(modelLuxury.luxury, modelLuxury.model)
                    .values(
                            queryFactory.select(luxury).from(luxury).where(luxury.id.eq(luxuryId)).fetchOne(),
                            queryFactory.select(model).from(model).where(model.id.eq(modelRequestDto.getId())).fetchOne()
                    )
                    .execute();
        });

        // Step 3: 업데이트된 모델과 관련된 럭셔리 정보 조회
        List<ModelResponseDto> response = getModelsWithLuxury(new BooleanBuilder().and(model.id.eq(modelRequestDto.getId())));

        return response.isEmpty() ? null : response.get(0);
    }

}
