package com.example.sitpass.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDto {


    private String name;


    private String surName;

    @NotNull
    private String address;


    private String phoneNumber;


    private LocalDate birthday;


    private String city;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;


    private String zipCode;

    private String imagePath;

}
