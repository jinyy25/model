package com.practice.cycle.model.service;

import com.practice.cycle.model.dto.ModelRequestDto;
import com.practice.cycle.model.dto.ModelResponseDto;
import com.practice.cycle.model.repository.ModelRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;


    //model 성별과 이름으로 검색
    @Transactional(readOnly = true)
    public List<ModelResponseDto> findModelsWithLuxury(ModelRequestDto modelRequestDto){
        return modelRepository.findModelsWithLuxury(modelRequestDto);
    }


    @Transactional
    public ModelResponseDto saveModel(ModelRequestDto modelRequestDto){
        return modelRepository.saveModelsWithLuxury(modelRequestDto);
    }

    @Transactional
    public void deleteModel(Long id) {

    }
}
