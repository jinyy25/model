package com.practice.cycle.model.dto;

import com.practice.cycle.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelRequestDto {
        private Long id;
        private String name;
        private Gender gender;
        private LocalDateTime createAt;
        private LocalDateTime updatedAt;
        private Long luxuryId;
        private List<Long> luxuryIds;
}
