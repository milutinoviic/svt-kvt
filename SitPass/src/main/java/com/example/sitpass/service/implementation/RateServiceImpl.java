package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.rate.RateCreateDto;
import com.example.sitpass.dto.rate.RateDto;
import com.example.sitpass.model.Rate;
import com.example.sitpass.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RateServiceImpl implements com.example.sitpass.service.RateService {
    private final RateRepository rateRepository;


    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate createRate(RateCreateDto rateDto) {
        Rate rate = new Rate();
        rate.setEquipment(rateDto.getEquipment());
        rate.setStaff(rateDto.getStaff());
        rate.setHygiene(rateDto.getHygiene());
        rate.setSpace(rateDto.getSpace());
        rateRepository.save(rate);
        return rate;

    }
    @Override
    public List<RateDto> getAllRates() {
        List<RateDto> rateDtos = new ArrayList<>();
        for (Rate rate : rateRepository.findAll()) {
            RateDto rateDto = convertToDto(rate);
            rateDtos.add(rateDto);
        }
        return rateDtos;
    }
    @Override
    public Rate getRateById(long id)
    {
        Rate rate = rateRepository.findById(id).orElse(null);
        return rate;
    }
    @Override
    public RateDto getRateByIdDto(long id) {
        Rate rate = rateRepository.findById(id).orElse(null);
        if (rate == null) {
            return null;
        }
        RateDto rateDto = convertToDto(rate);
        return rateDto;

    }
    @Override
    public RateDto convertToDto(Rate rate){
        RateDto rateDto = new RateDto();
        rateDto.setId(rate.getId());
        rateDto.setEquipment(rate.getEquipment());
        rateDto.setStaff(rate.getStaff());
        rateDto.setHygiene(rate.getHygiene());
        rateDto.setSpace(rate.getSpace());
        return rateDto;

    }
    @Override
    public RateDto updateRate(Long id, RateCreateDto rateDto) {
        Rate rate = rateRepository.findById(id).orElse(null);
        if (rate == null) {
            return null;
        }
        rate.setEquipment(rateDto.getEquipment());
        rate.setStaff(rateDto.getStaff());
        rate.setHygiene(rateDto.getHygiene());
        rate.setSpace(rateDto.getSpace());

        rateRepository.save(rate);

        RateDto rateDto1 = convertToDto(rate);


        return rateDto1;

    }
    @Override
    public RateDto deleteRate(long id) {
        Rate rate = getRateById(id);
        if (rate == null) {
            return null;
        }
        rateRepository.deleteById(id);
        RateDto rateDto = convertToDto(rate);
        return rateDto;

    }


}
