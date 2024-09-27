package com.example.sitpass.dto.exercise;

import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExerciseDto {
    private Long id;
    private LocalDateTime from;
    private LocalDateTime until;
    private UserDto user;
    private FacilityDto facility;
}
