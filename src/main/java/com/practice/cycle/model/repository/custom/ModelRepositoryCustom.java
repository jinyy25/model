package com.practice.cycle.model.repository.custom;

import com.practice.cycle.model.dto.ModelRequestDto;
import com.practice.cycle.model.dto.ModelResponseDto;
import com.practice.cycle.model.entity.Gender;
import com.practice.cycle.model.entity.Model;

import java.util.List;

public interface ModelRepositoryCustom {
    List<ModelResponseDto> findModelsWithLuxury(ModelRequestDto modelRequestDto);
    ModelResponseDto saveModelsWithLuxury(ModelRequestDto modelRequestDto);
    ModelResponseDto updateModelsWithLuxury(ModelRequestDto modelRequestDto);

}
