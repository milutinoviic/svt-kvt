package com.example.sitpass.controller;

import com.example.sitpass.dto.facility.FacilityCreateDto;
import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.dto.workDay.WorkDayRequest;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.model.WorkDay;
import com.example.sitpass.service.implementation.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facility")
@CrossOrigin("*")
public class FacilityController {
    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping
    public ResponseEntity<List<FacilityDto>> getAllFacilities() {
        List<FacilityDto> facilitiesDto = facilityService.getAllFacilities();
        return new ResponseEntity<>(facilitiesDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FacilityDto>> searchFacilities(@RequestParam Optional<String> city, @RequestParam Optional<Long> disciplineId, @RequestParam Optional<Double> fromRate, @RequestParam Optional<Double> toRate, @Valid @RequestParam Optional<LocalTime> fromWorkTime, @RequestParam Optional<LocalTime> toWorkTime) {

//        System.out.println(city.orElse("city not defined"));
//        System.out.println(disciplineId.orElse(125L));
        List<FacilityDto> facilityDtoList = facilityService.searchFacilities(city, disciplineId, fromRate, toRate, fromWorkTime, toWorkTime);
        return new ResponseEntity<>(facilityDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacilityById(@PathVariable long id) {
        FacilityDto facilityDto = facilityService.getFacilityByIdDto(id); // facilityDto
        if (facilityDto != null) {
            return new ResponseEntity<>(facilityDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(@Valid @RequestBody FacilityCreateDto facility) {
        FacilityDto createdFacilityDto = facilityService.createFacility(facility);
        if (createdFacilityDto != null) {
            return new ResponseEntity<>(createdFacilityDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PutMapping("/{facilityId}/users/{userId}/status")
    public void updateFacilityStatus(@PathVariable Long facilityId, @PathVariable Long userId) {
        facilityService.updateFacilityAttributeActive(facilityId, userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDto> updateFacility(@PathVariable Long id, @RequestBody FacilityCreateDto facility) {
        FacilityDto updatedFacilityDto = facilityService.updateFacility(id, facility);
        return new ResponseEntity<>(updatedFacilityDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacilityDto> deleteFacility(@PathVariable long id) {
        FacilityDto deletedFacilityDto = facilityService.deleteFacility(id);
        if (deletedFacilityDto != null) {
            return new ResponseEntity<>(deletedFacilityDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("workdays/{id}")
    public ResponseEntity<List<WorkDayDto>> findWorkDaysfromFacilityById(@PathVariable long id){
        List<WorkDayDto> workDaysDto = facilityService.findWorkDaysByFacilityId(id);
        if (workDaysDto != null) {
            return new ResponseEntity<>(workDaysDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("Allworkdays/{id}")
    public ResponseEntity<List<WorkDayDto>> findAllWorkDaysByFacilityId(@PathVariable long id){
        List<WorkDayDto> workDaysDto = facilityService.findAllWorkDaysByFacilityId(id);
        if (workDaysDto != null) {
            return new ResponseEntity<>(workDaysDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/{facilityId}/workdays")
    public ResponseEntity<Void> addWorkDay(@PathVariable Long facilityId, @RequestBody WorkDayCreateDto workDayDto) throws FacilityNotFoundException{
        try {
            facilityService.addWorkDayToFacility(facilityId, workDayDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (FacilityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<FacilityDto>> getAllInactiveFacilities() {
        List<FacilityDto> facilitiesDto = facilityService.getAllInactiveFacilities();
        return new ResponseEntity<>(facilitiesDto, HttpStatus.OK);
    }


    @DeleteMapping("/{facilityId}/workdays")
    public ResponseEntity<WorkDayDto> removeWorkDayByDate(@PathVariable Long facilityId, @RequestBody WorkDayRequest workDayRequest) {
        try {

            LocalDate localDate = workDayRequest.getValidFrom();
            WorkDayDto workDay = facilityService.findAndRemoveWorkDayByDate(facilityId, localDate);
            return new ResponseEntity<>(workDay,HttpStatus.OK);


        } catch (FacilityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


    @GetMapping("/searchForHomePage")
    public ResponseEntity<List<FacilityDto>> searchFacilities(
            @RequestParam Optional<String> city,
            @RequestParam Optional<LocalDate> day) {

        List<FacilityDto> facilityDtoList = facilityService.searchForHomePage(city,day);
        return new ResponseEntity<>(facilityDtoList, HttpStatus.OK);
    }

}

