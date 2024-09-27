package com.example.sitpass.service;
import com.example.sitpass.dto.exercise.ExerciseCreateDto;
import com.example.sitpass.dto.exercise.ExerciseDto;

import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Exercise;

import java.util.List;


public interface ExerciseService {

    ExerciseDto createExercise(ExerciseCreateDto exerciseCreateDto) throws UserNotFoundException, FacilityNotFoundException;

    List<ExerciseDto> getAllExercises();

    Exercise getExerciseById(long id);

    ExerciseDto getExerciseByIdDto(long id);

    ExerciseDto convertExerciseToDto(Exercise exercise);

    ExerciseDto updateExercise(Long id, ExerciseCreateDto exerciseDetails);

    ExerciseDto deleteExercise(long id);

    Long getCountExerciseByFacilityAndUser(Long facilityId,Long userId);
}
