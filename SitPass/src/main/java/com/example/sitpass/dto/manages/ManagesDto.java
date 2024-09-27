package com.example.sitpass.dto.manages;

import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagesDto {
    private Long id;
    private LocalDate startData;
    private LocalDate endData;
    private UserDto user;
    private FacilityDto facillty;
}
