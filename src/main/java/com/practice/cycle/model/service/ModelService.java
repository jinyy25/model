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

        // Model 엔티티 저장
//        Model model = modelRequestDto.toModelEntity(modelRequestDto);
//        modelRepository.save(model);
//
//        // ModelLuxury 엔티티 저장
//        List<ModelLuxury> modelLuxuries = modelRequestDto.toModelLuxuryEntities(modelRequestDto.getLuxuries(), model);
//        modelLuxuryRepository.saveAll(modelLuxuries);
//
//        return ModelResponseDto.toModelDto(model);
        return modelRepository.saveModelsWithLuxury(modelRequestDto);
    }

    @Transactional
    public void deleteModel(Long id) {

        // Model 엔티티 찾기
//        Model model = modelRepository.findById(id).orElseThrow(ModelNotfoundException::new);
//
//        // Model 엔티티 삭제 (CascadeType.ALL 덕분에 관련된 ModelLuxury 삭제됨)
//        modelRepository.delete(model);

        // 만료일 경우 중단
    }
}
