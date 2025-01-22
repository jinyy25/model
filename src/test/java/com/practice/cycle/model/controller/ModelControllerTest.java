package com.practice.cycle.model.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.cycle.model.dto.ModelRequestDto;
import com.practice.cycle.model.entity.Gender;
import com.practice.cycle.model.service.ModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;




import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(MockitoExtension.class)
class ModelControllerTest {

    @InjectMocks
    ModelController modelController;

    @Mock
    ModelService modelService;

    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(modelController).build();
    }

    @Test
    @DisplayName("Model 작성")
    public void saveModelTest() throws Exception {
        // given
        ModelRequestDto modelReq = new ModelRequestDto(null,"tdd", Gender.M,null,null,null,null);

        // when, then
        mockMvc.perform(
                post("/api/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(modelReq)))
                .andExpect(status().isCreated());

        verify(modelService).saveModel(modelReq);

    }

    @Test
    @DisplayName("Model 조회")
    public void findModelTest() throws Exception {
        // given
        ModelRequestDto modelReq = new ModelRequestDto(null,"tdd", Gender.M,null,null,null,null);

        // when, then
        mockMvc.perform(
                post("/api/test")
                .contentType(MediaType.APPLICATION_JSON) // 요청 본문 타입 설정
                .content(objectMapper.writeValueAsString(modelReq)) // 요청 본문에 JSON 데이터 추가
        ).andExpect(status().isOk());

        verify(modelService).findModelsWithLuxury(modelReq);
    }

    @Test
    @DisplayName("Model 수정")
    public void updateModelTest() throws Exception{
        // given
        ModelRequestDto modelReq = new ModelRequestDto(null,"tdd", Gender.M,null,null,null,null);

        // when, then
        mockMvc.perform(
                post("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelReq)))
                .andExpect(status().isCreated());


        verify(modelService).updateModel(modelReq);
    }





}