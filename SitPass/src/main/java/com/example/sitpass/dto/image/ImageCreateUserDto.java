package com.example.sitpass.dto.image;

import com.example.sitpass.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ImageCreateUserDto {

    @NotNull
    private String path;
    @NotNull
    private Long userId;
}
