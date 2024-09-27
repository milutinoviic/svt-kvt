package com.example.sitpass.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {
    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be blank")
    private String email;
    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be blank")
    private String password;
}
