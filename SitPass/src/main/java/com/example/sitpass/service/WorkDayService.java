package com.example.sitpass.service;

import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.model.WorkDay;

import java.util.List;

public interface WorkDayService {

    WorkDayDto createWorkDay(WorkDayCreateDto workDayDto);

    WorkDayDto convertToDto(WorkDay workDay);

    WorkDay convertDtoToWorkDay(WorkDayCreateDto workDayDto);

    List<WorkDayDto> getAllWorkDaysDto();

    WorkDay getWorkDayById(long id);

    WorkDayDto getWorkDayByIdDto(long id);

    WorkDayDto updateWorkDay(Long id, WorkDayCreateDto workDayDetails);

    WorkDayDto deleteWorkDay(Long id);
}
