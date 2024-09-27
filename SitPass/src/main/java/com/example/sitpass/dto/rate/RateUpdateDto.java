package com.example.sitpass.dto.rate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RateUpdateDto {
    @NotNull
    private Integer equipment;
    @NotNull
    private Integer staff;
    @NotNull
    private Integer hygiene;
    @NotNull
    private Integer space;
    @NotNull
    private Long rateId;
}
