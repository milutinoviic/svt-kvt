package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.exercise.ExerciseCreateDto;
import com.example.sitpass.dto.exercise.ExerciseDetailsDto;
import com.example.sitpass.dto.exercise.ExerciseDto;
import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Exercise;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService implements com.example.sitpass.service.ExerciseService {

    private final UserService userService;
    private final FacilityService facilityService;
    private final ExerciseRepository exerciseRepository;


    public ExerciseService(UserService userService, FacilityService facilityService, ExerciseRepository exerciseRepository) {
        this.userService = userService;
        this.facilityService = facilityService;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseDto createExercise(ExerciseCreateDto exerciseCreateDto) throws UserNotFoundException, FacilityNotFoundException {
        Exercise exercise = new Exercise();
        exercise.setFrom(exerciseCreateDto.getFrom());
        exercise.setUntil(exerciseCreateDto.getUntil());
        User user = userService.getUserById(exerciseCreateDto.getUserId());
        if(user==null){
            throw new UserNotFoundException("Not Found User");
        }
        exercise.setUser(user);
        Facility facility = facilityService.getFacilityById(exerciseCreateDto.getFacilityId());
        if(facility ==null){
            throw new FacilityNotFoundException("Not Found Facility");
        }
        exercise.setFacility(facility);

        exerciseRepository.save(exercise);

        ExerciseDto exerciseDto = convertExerciseToDto(exercise);

        return exerciseDto;




    }
    @Override
    public List<ExerciseDto> getAllExercises() {
       List<ExerciseDto> exerciseDtos = new ArrayList<>();
       for (Exercise exercise : exerciseRepository.findAll()) {
           ExerciseDto exerciseDto = convertExerciseToDto(exercise);
           exerciseDtos.add(exerciseDto);
       }
       return exerciseDtos;
    }

    @Override
    public Exercise getExerciseById(long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        return exercise.orElse(null);
    }

    @Override
    public ExerciseDto getExerciseByIdDto(long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if(exercise.isEmpty()){
            return null;
        }
        ExerciseDto exerciseDto = convertExerciseToDto(exercise.get());
        return exerciseDto;
    }

    @Override
    public ExerciseDto convertExerciseToDto(Exercise exercise) {
        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setId(exercise.getId());
        exerciseDto.setFrom(exercise.getFrom());
        exerciseDto.setUntil(exercise.getUntil());
        UserDto userDto = userService.getUserByIdDto(exercise.getUser().getId());
        exerciseDto.setUser(userDto);
        FacilityDto facilityDto = facilityService.getFacilityByIdDto(exercise.getFacility().getId());
        exerciseDto.setFacility(facilityDto);

        return exerciseDto;

    }
    @Override
    public ExerciseDto updateExercise(Long id, ExerciseCreateDto exerciseDetails) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if(exerciseOptional.isEmpty()) {
            return null;
        }

        Exercise exercise = exerciseOptional.get();
        User user = userService.getUserById(exerciseDetails.getUserId());
        exercise.setUser(user);
        Facility facility = facilityService.getFacilityById(exerciseDetails.getFacilityId());
        exercise.setFacility(facility);

        exerciseRepository.save(exercise);

        ExerciseDto exerciseDto = convertExerciseToDto(exercise);


        return exerciseDto;

    }
    @Override
    public ExerciseDto deleteExercise(long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if(exercise.isEmpty()) {
            return null;
        }
        exerciseRepository.deleteById(id);
        ExerciseDto exerciseDto = convertExerciseToDto(exercise.get());

        return exerciseDto;
    }

    @Override
    public Long getCountExerciseByFacilityAndUser(Long facilityId,Long userId) {
        LocalDateTime now = LocalDateTime.now();
        long countExercise=exerciseRepository.countByFacilityIdAndUserIdAndDateBefore(facilityId,userId,now);
        return countExercise;
    }

    public ExerciseDetailsDto convertExerciseToDtoDetail(Exercise exercise) {
        ExerciseDetailsDto exerciseDto = new ExerciseDetailsDto();

        exerciseDto.setFrom(exercise.getFrom());
        exerciseDto.setUntil(exercise.getUntil());

        FacilityDto facilityDto = facilityService.getFacilityByIdDto(exercise.getFacility().getId());
        exerciseDto.setFacilityName(facilityDto.getName());

        return exerciseDto;

    }


    public List<ExerciseDetailsDto> getExercisesByUserId(Long userId) {
        List<Exercise> exercises = exerciseRepository.findAllByUserId(userId);
        List<ExerciseDetailsDto> exerciseDtos = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseDtos.add(convertExerciseToDtoDetail(exercise));
        }
        return exerciseDtos;
    }


}
