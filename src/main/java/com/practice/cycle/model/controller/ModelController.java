package com.practice.cycle.model.controller;
import com.practice.cycle.model.dto.ModelRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.practice.cycle.model.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    //이름, 성별, category로 검색
    @PostMapping("/api/test")
    public ResponseEntity<?> findModelsWithLuxury(@RequestBody ModelRequestDto modelRequestDto){
        return ResponseEntity.ok(modelService.findModelsWithLuxury(modelRequestDto));
    }

    @PostMapping("/api/add")
    public ResponseEntity<?> insertModel(@RequestBody ModelRequestDto modelRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.saveModel(modelRequestDto));
    }

    @PostMapping("/api/update")
    public ResponseEntity updateModel(@RequestBody ModelRequestDto modelRequestDto){
        return ResponseEntity.ok(modelService.updateModel(modelRequestDto));
    }



}
