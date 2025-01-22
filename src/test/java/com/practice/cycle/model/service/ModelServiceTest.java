package com.practice.cycle.model.service;

import com.practice.cycle.model.dto.ModelRequestDto;
import com.practice.cycle.model.dto.ModelResponseDto;
import com.practice.cycle.model.entity.Gender;
import com.practice.cycle.model.entity.Model;
import com.practice.cycle.model.repository.ModelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class ModelServiceTest {

    @InjectMocks
    ModelService modelService;

    @Mock
    ModelRepository modelRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    @DisplayName("Model 조회 서비스")
    void findModelTest(){
        //given
        ModelRequestDto model1 = new ModelRequestDto(1L,"decive", Gender.M,null,null,null,null);
        ModelResponseDto model2 = new ModelResponseDto(1L,"decive", Gender.M,null,null,null);

        List<ModelResponseDto> models = new ArrayList<>();
        models.add(model2);

        //given
        given(modelRepository.findModelsWithLuxury(model1)).willReturn(models);


        //when
        List<ModelResponseDto> result = modelService.findModelsWithLuxury(model1);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("decive");

    }

    @Test
    @DisplayName("Model 추가")
    void saveModelTest(){
        // given
        ModelRequestDto model1 = new ModelRequestDto(1L,"Isaac", Gender.M,null,null,null,null);
        ModelResponseDto model2 = new ModelResponseDto(1L,"Isaac", Gender.M,null,null,null);

        given(modelRepository.saveModelsWithLuxury(model1)).willReturn(model2);

        //when
        modelService.saveModel(model1);

        //then
        verify(modelRepository).saveModelsWithLuxury(any());

    }

    @Test
    @DisplayName("model 수정")
    void deleteModelTest(){
        // given
        ModelRequestDto model1 = new ModelRequestDto(1L,"re", Gender.M,null,null,null,null);
        ModelResponseDto model2 = new ModelResponseDto(1L,"re", Gender.M,null,null,null);

        given(modelRepository.updateModelsWithLuxury(model1)).willReturn(model2);

        // when
        modelService.updateModel(model1);

        // then
        verify(modelRepository).updateModelsWithLuxury(any());

    }




}