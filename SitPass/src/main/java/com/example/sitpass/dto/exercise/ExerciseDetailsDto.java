package com.example.sitpass.dto.exercise;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExerciseDetailsDto {

    private LocalDateTime from;
    private LocalDateTime until;
    private String facilityName;
}
