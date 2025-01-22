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
    @DisplayName("전체 Model 조회 서비스")
    void findAllModel(){
        //given
        List<Model> models = new ArrayList<>();
        Model model1 = new Model(5L,"decive", Gender.M,null,null);
        Model model2 = new Model(6L,"helena", Gender.F,null,null);
        models.add(model1);
        models.add(model2);

        given(modelRepository.findAll()).willReturn(models);


        //when
        List<ModelResponseDto> result = modelService.getModelList();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("decive");
        assertThat(result.get(1).getName()).isEqualTo("helena");
    }

    @Test
    @DisplayName("Model 추가")
    void addModelTest(){
        // given
        Model model = new Model(1L,"Isaac", Gender.M,null,null);
        ModelRequestDto modelRequestDTO = new ModelRequestDto(1L,"Isaac", Gender.M,null,null);

        given(modelRepository.save(model)).willReturn(model);

        //when
        modelService.saveModel(modelRequestDTO);

        //then
        verify(modelRepository).save(any());

    }

    @Test
    @DisplayName("model 삭제")
    void deleteModelTest(){
        // given
        Model model = new Model(1L,"Isaac", Gender.M,null,null);
        given(modelRepository.findById(anyLong())).willReturn(Optional.of(model));

        // when
        modelService.deleteModel(1L);

        // then
        verify(modelRepository).deleteById(anyLong());

    }




}