package com.example.sitpass.controller;

import com.example.sitpass.dto.exercise.ExerciseCreateDto;
import com.example.sitpass.dto.exercise.ExerciseDetailsDto;
import com.example.sitpass.dto.exercise.ExerciseDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.service.implementation.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        List<ExerciseDto> exercisesDto = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercisesDto, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) {
        ExerciseDto exerciseDto = exerciseService.getExerciseByIdDto(id);
        if (exerciseDto != null) {
            return new ResponseEntity<>(exerciseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@Valid @RequestBody ExerciseCreateDto exercise) {
        try{
            ExerciseDto newExerciseDto = exerciseService.createExercise(exercise);
            return new ResponseEntity<>(newExerciseDto, HttpStatus.CREATED);
        }catch (UserNotFoundException u) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (FacilityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable Long id, @RequestBody ExerciseCreateDto exercise) {
        ExerciseDto updateExerciseDto = exerciseService.updateExercise(id,exercise);
        return new ResponseEntity<>(updateExerciseDto, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ExerciseDto> deleteExercise(@PathVariable Long id) {
        ExerciseDto exerciseDto = exerciseService.deleteExercise(id);
        if (exerciseDto != null) {
            return new ResponseEntity<>(exerciseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byFacility/{facilityId}/{userId}")
    public ResponseEntity<Long> getAllExercisesByUserAndFacility(@PathVariable Long facilityId, @PathVariable Long userId) {
        Long exerciseCount = exerciseService.getCountExerciseByFacilityAndUser(facilityId,userId);
        return new ResponseEntity<>(exerciseCount, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExerciseDetailsDto>> getExercisesByUserId(@PathVariable Long userId) {
        List<ExerciseDetailsDto> exerciseDtos = exerciseService.getExercisesByUserId(userId);
        return new ResponseEntity<>(exerciseDtos, HttpStatus.OK);
    }


}
