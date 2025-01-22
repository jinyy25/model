package com.practice.cycle.model.dto;


import com.practice.cycle.model.entity.Gender;
import com.practice.cycle.model.entity.Model;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private List<String> luxuries;  // luxuries 리스트 추가

    @QueryProjection
    public ModelResponseDto(Long id, String name, Gender gender, String luxuries){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.luxuries = Arrays.asList(luxuries.split(","));
    }
}
