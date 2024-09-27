package com.example.sitpass.dto.rate;

import lombok.Data;

@Data
public class RateDto {
    private Long id;
    private Integer equipment;
    private Integer staff;
    private Integer hygiene;
    private Integer space;

}
