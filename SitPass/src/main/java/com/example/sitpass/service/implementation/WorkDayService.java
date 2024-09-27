package com.example.sitpass.service.implementation;


import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.model.WorkDay;
import com.example.sitpass.repository.WorkDayRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayService implements com.example.sitpass.service.WorkDayService {

    private final WorkDayRepository workDayRepository;


    public WorkDayService(WorkDayRepository workDayRepository) {
        this.workDayRepository = workDayRepository;
    }



    @Override
    public WorkDayDto createWorkDay(WorkDayCreateDto workDayDto) {
        WorkDay workDay = new WorkDay();
        workDay.setValidFrom(workDayDto.getValidFrom());
        workDay.setDay(workDayDto.getDay());
        workDay.setFrom(workDayDto.getFrom());
        workDay.setUntil(workDayDto.getUntil());

        workDayRepository.save(workDay);
        WorkDayDto workDayDto1 = convertToDto(workDay);


        return workDayDto1;


    }

    @Override
    public WorkDayDto convertToDto(WorkDay workDay) {
        WorkDayDto workDayDto = new WorkDayDto();
        workDayDto.setId(workDay.getId());
        workDayDto.setValidFrom(workDay.getValidFrom());
        workDayDto.setDay(workDay.getDay());
        workDayDto.setFrom(workDay.getFrom());
        workDayDto.setUntil(workDay.getUntil());

        return workDayDto;


    }
    @Override
    public WorkDay convertDtoToWorkDay(WorkDayCreateDto workDayDto) {
        WorkDay workDay = new WorkDay();
        workDay.setValidFrom(workDayDto.getValidFrom());
        workDay.setDay(workDayDto.getDay());
        workDay.setFrom(workDayDto.getFrom());
        workDay.setUntil(workDayDto.getUntil());
        return workDay;
    }


    @Override
    public List<WorkDayDto> getAllWorkDaysDto() {
        List<WorkDayDto> workDayDtos = new ArrayList<>();
        for (WorkDay workDay : workDayRepository.findAll()) {
            WorkDayDto workDayDto = convertToDto(workDay);
            workDayDtos.add(workDayDto);
        }
        return workDayDtos;

    }
    @Override
    public WorkDay getWorkDayById(long id) {
        Optional<WorkDay> workDay = workDayRepository.findById(id);
        return workDay.orElse(null);
    }
    @Override
    public WorkDayDto getWorkDayByIdDto(long id) {
        Optional<WorkDay> workDay = workDayRepository.findById(id);
        if(workDay.isEmpty()){
            return null;
        }
        WorkDayDto workDayDto = convertToDto(workDay.get());
        return workDayDto;
    }

    @Override
    public WorkDayDto updateWorkDay(Long id, WorkDayCreateDto workDayDetalis) {
        WorkDay workDay = workDayRepository.findById(id).orElse(null);
        if (workDay == null) {
            return null;
        }

        workDay.setValidFrom(workDayDetalis.getValidFrom());
        workDay.setDay(workDayDetalis.getDay());
        workDay.setFrom(workDayDetalis.getFrom());
        workDay.setUntil(workDayDetalis.getUntil());
        workDayRepository.save(workDay);
        WorkDayDto workDayDto = convertToDto(workDay);
        return workDayDto;


    }
    @Override
    public WorkDayDto deleteWorkDay(Long id) {
        WorkDay workDay = workDayRepository.findById(id).orElse(null);
        if (workDay == null) {
          return null;
        }
        workDayRepository.deleteById(id);
        WorkDayDto workDayDto = convertToDto(workDay);

        return workDayDto;
    }



}
