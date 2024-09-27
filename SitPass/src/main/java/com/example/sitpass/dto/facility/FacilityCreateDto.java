package com.example.sitpass.dto.facility;

import com.example.sitpass.dto.discipline.DisciplineCreateDto;
import com.example.sitpass.dto.image.ImageCreateFacilityDto;
import com.example.sitpass.dto.workDay.WorkDayCreateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FacilityCreateDto {

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private boolean active;
    @NotNull
    private List<WorkDayCreateDto> workDays;
    @NotNull
    private List<ImageCreateFacilityDto> images;
    @NotNull
    private List<Long> disciplinesIds;





}
