package com.example.sitpass.dto.image;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ImageCreateFacilityDto {
    @NotNull
    private String path;
}
