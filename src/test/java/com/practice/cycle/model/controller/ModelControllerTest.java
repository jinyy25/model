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
        ModelRequestDto modelReq = new ModelRequestDto(null,"tdd", Gender.M,null,null);

        // when, then
        mockMvc.perform(
                post("/api/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(modelReq)))
                .andExpect(status().isCreated());

        verify(modelService).saveModel(modelReq);

    }

    @Test
    @DisplayName("전체 Model 조회")
    public void allModelTest() throws Exception {
        // given

        // when, then
        mockMvc.perform(
                get("/api/test"))
                .andExpect(status().isOk());

        verify(modelService).getModelList();
    }

    @Test
    @DisplayName("Model 삭제")
    public void deleteModelTest() throws Exception{
        //given
        Long id = 1L;

        //when,then
        mockMvc.perform(
                delete("/api/delete/{id}",id))
                .andExpect(status().isOk());

        verify(modelService).deleteModel(id);
    }





}