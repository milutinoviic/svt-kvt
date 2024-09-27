package com.example.sitpass.controller;

import com.example.sitpass.dto.manages.ManagesCreateDto;
import com.example.sitpass.dto.manages.ManagesDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.repository.ManagesRepository;
import com.example.sitpass.service.implementation.ManagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manages")
public class ManagesController {

    private final ManagesService managesService;
    private final ManagesRepository managesRepository;

    @Autowired
    public ManagesController(ManagesService managesService, ManagesRepository managesRepository) {
        this.managesService = managesService;
        this.managesRepository = managesRepository;
    }

    @GetMapping
    public ResponseEntity<List<ManagesDto>> getAllManages() {
        List<ManagesDto> managesList = managesService.getAllManages();
        return new ResponseEntity<>(managesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagesDto> getManagesById(@PathVariable long id) {
        ManagesDto manages = managesService.getManagesByIdDto(id);
        if (manages != null) {
            return new ResponseEntity<>(manages, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ManagesDto> createManages(@Valid @RequestBody ManagesCreateDto manages) {
       try {
           ManagesDto createdManages = managesService.createManages(manages);
           return new ResponseEntity<>(createdManages, HttpStatus.CREATED);
       }catch (UserNotFoundException u) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }catch (FacilityNotFoundException e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagesDto> updateManages(@PathVariable Long id, @RequestBody ManagesCreateDto manages) {
        ManagesDto updatedManages = managesService.updateManages(id, manages);
        return new ResponseEntity<>(updatedManages, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ManagesDto> deleteManages(@PathVariable long id) {
        ManagesDto deletedManages = managesService.deleteManages(id);
        if (deletedManages != null) {
            return new ResponseEntity<>(deletedManages, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{facilityId}/isManager")
    public ResponseEntity<Boolean> isManager(@PathVariable Long userId,@PathVariable Long facilityId) {
        boolean isManager = managesRepository.existsByUserIdAndFacilityId(userId,facilityId);
        return ResponseEntity.ok(isManager);
    }

    @GetMapping("/check/{facilityId}")
    public ResponseEntity<Boolean> checkManagerExists(@PathVariable Long facilityId) {
        boolean exists = managesService.checkManagerExistsForFacility(facilityId);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @DeleteMapping("/facility/{facilityId}")
    public ResponseEntity<Void> removeManagerForFacility(@PathVariable Long facilityId) {
        managesService.removeManagerByFacilityId(facilityId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/facility-id")
    public ResponseEntity<Long> getFacilityIdForUser(@PathVariable Long userId) {
        Long facilityId = managesService.getFacilityIdForUser(userId);
        return ResponseEntity.ok(facilityId);
    }

}

