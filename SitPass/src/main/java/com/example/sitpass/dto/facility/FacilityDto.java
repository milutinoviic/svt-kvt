package com.example.sitpass.dto.facility;

import com.example.sitpass.dto.discipline.DisciplineDto;
import com.example.sitpass.dto.image.ImageFacilityDto;
import com.example.sitpass.dto.workDay.WorkDayDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FacilityDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private String address;
    private String city;
    private Double totalRating;
    private boolean active;
    private List<WorkDayDto> workDays;
    private List<ImageFacilityDto> images;
    private List<DisciplineDto> disciplines;
}
