package com.practice.cycle.model.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.practice.cycle.model.dto.QModelResponseDto is a Querydsl Projection type for ModelResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QModelResponseDto extends ConstructorExpression<ModelResponseDto> {

    private static final long serialVersionUID = 1884205335L;

    public QModelResponseDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<com.practice.cycle.model.entity.Gender> gender, com.querydsl.core.types.Expression<String> luxuries) {
        super(ModelResponseDto.class, new Class<?>[]{String.class, com.practice.cycle.model.entity.Gender.class, String.class}, name, gender, luxuries);
    }

}

