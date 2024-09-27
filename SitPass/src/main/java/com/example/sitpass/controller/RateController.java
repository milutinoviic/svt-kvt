package com.example.sitpass.controller;

import com.example.sitpass.dto.rate.RateCreateDto;
import com.example.sitpass.dto.rate.RateDto;
import com.example.sitpass.service.implementation.RateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {

    private final RateServiceImpl rateService;

    @Autowired
    public RateController(RateServiceImpl rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public ResponseEntity<List<RateDto>> getAllRates() {
        List<RateDto> ratesDto = rateService.getAllRates();
        return new ResponseEntity<>(ratesDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateDto> getRateById(@PathVariable long id) {
        RateDto rateDto = rateService.getRateByIdDto(id);
        if (rateDto != null) {
            return new ResponseEntity<>(rateDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping
//    public ResponseEntity<RateDto> createRate(@Valid @RequestBody RateCreateDto rate) {
//        RateDto createdRateDto = rateService.createRate(rate);
//        return new ResponseEntity<>(createdRateDto, HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<RateDto> updateRate(@PathVariable Long id, @RequestBody RateCreateDto rate) {
        RateDto updatedRateDto = rateService.updateRate(id, rate);
        return new ResponseEntity<>(updatedRateDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RateDto> deleteRate(@PathVariable long id) {
        RateDto deletedRateDto = rateService.deleteRate(id);
        if (deletedRateDto != null) {
            return new ResponseEntity<>(deletedRateDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

