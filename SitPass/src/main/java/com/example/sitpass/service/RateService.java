package com.example.sitpass.service;

import com.example.sitpass.dto.rate.RateCreateDto;
import com.example.sitpass.dto.rate.RateDto;
import com.example.sitpass.model.Rate;

import java.util.List;

public interface RateService {

    Rate createRate(RateCreateDto rateDto);

    List<RateDto> getAllRates();

    Rate getRateById(long id);

    RateDto getRateByIdDto(long id);

    RateDto convertToDto(Rate rate);

    RateDto updateRate(Long id, RateCreateDto rateDto);

    RateDto deleteRate(long id);
}
