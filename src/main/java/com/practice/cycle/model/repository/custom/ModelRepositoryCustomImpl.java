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

        return  queryFactory.select(
                new QModelResponseDto(
                        model.name
                        , model.gender
                        , Expressions.stringTemplate("GROUP_CONCAT({0})", luxury.name).as("luxuries")))
                .from(model)
                .innerJoin(model.modelLuxuries, modelLuxury) // 모델과 모델-럭셔리 중간 테이블 조인
                .innerJoin(luxury).on(modelLuxury.luxury.eq(luxury)) // 모델-럭셔리와 럭셔리 테이블을 조인
                .where(builder)
                .groupBy(model.name, model.gender)
                .fetch();
    }


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

        return queryFactory.select(
                new QModelResponseDto(
                        model.name,
                        model.gender,
                        Expressions.stringTemplate("GROUP_CONCAT({0})", luxury.name).as("luxuries")
                    )
                )
                .from(model)
                .innerJoin(model.modelLuxuries, modelLuxury)
                .innerJoin(luxury).on(modelLuxury.luxury.eq(luxury))
                .where(model.id.eq(modelId))
                .groupBy(model.name, model.gender)
                .fetchOne();
    }




}
