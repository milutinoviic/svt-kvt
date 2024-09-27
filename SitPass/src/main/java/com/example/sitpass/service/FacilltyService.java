package com.example.sitpass.service;

import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.facility.FacilityCreateDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import com.example.sitpass.model.Facility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface FacilltyService {



    FacilityDto createFacility(FacilityCreateDto facilityDetails);

    FacilityDto convertToDto(Facility facility);

    List<FacilityDto> getAllFacilities();

    Facility getFacilityById(long id);

    FacilityDto getFacilityByIdDto(long id);

    FacilityDto updateFacility(Long id, FacilityCreateDto facilityDetails);

    FacilityDto deleteFacility(long id);

    List<WorkDayDto>findWorkDaysByFacilityId(long id);

    List<FacilityDto> searchFacilities( Optional<String> city,  Optional<Long> disciplineId,  Optional<Double> fromRate,  Optional<Double> toRate,  Optional<LocalTime> fromWorkTime,  Optional<LocalTime> toWorkTime);

}
