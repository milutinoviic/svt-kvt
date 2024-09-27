package com.example.sitpass.dto.rate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RateCreateDto {

    @NotNull
    private Integer equipment;
    @NotNull
    private Integer staff;
    @NotNull
    private Integer hygiene;
    @NotNull
    private Integer space;


}
