package com.example.sitpass.dto.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExerciseCreateDto {

    @NotNull
    private LocalDateTime from;
    @NotNull
    private LocalDateTime until;
    @NotNull
    private Long userId;
    @NotNull
    private Long facilityId;


}
