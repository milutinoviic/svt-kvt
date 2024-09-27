package com.example.sitpass.controller;

import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.service.implementation.WorkDayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workdays")
public class WorkDayController {

    private final WorkDayService workDayService;

    @Autowired
    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WorkDayDto>> getAllWorkDays() {
        List<WorkDayDto> workDaysDto = workDayService.getAllWorkDaysDto();
        return new ResponseEntity<>(workDaysDto, HttpStatus.OK);
    }

//    @GetMapping("/fac/{id}")
//    public ResponseEntity<List<WorkDayDto>> getAllWorkDaysForFacility(@PathVariable Long id) {
//        List<WorkDayDto> workDaysDto = workDayService.getWorkDaysByFacilityId(id);
//        return new ResponseEntity<>(workDaysDto, HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkDayDto> getWorkDayById(@PathVariable long id) {
        WorkDayDto workDayDto = workDayService.getWorkDayByIdDto(id);
        if (workDayDto != null) {
            return new ResponseEntity<>(workDayDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<WorkDayDto> createWorkDay(@Valid @RequestBody WorkDayCreateDto workDay) {
        WorkDayDto createdWorkDayDto = workDayService.createWorkDay(workDay);
        return new ResponseEntity<>(createdWorkDayDto, HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Invalid input: " + ex.getBindingResult().toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDayDto> updateWorkDay(@PathVariable Long id, @RequestBody WorkDayCreateDto workDay) {
        WorkDayDto updatedWorkDayDto = workDayService.updateWorkDay(id, workDay);
        return new ResponseEntity<>(updatedWorkDayDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WorkDayDto> deleteWorkDay(@PathVariable long id) {
        WorkDayDto deletedWorkDayDto = workDayService.deleteWorkDay(id);
        if (deletedWorkDayDto != null) {
            return new ResponseEntity<>(deletedWorkDayDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}


