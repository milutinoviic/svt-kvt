package com.example.sitpass.dto.discipline;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DisciplineCreateDto {

    @NotNull
    private String name;

}
