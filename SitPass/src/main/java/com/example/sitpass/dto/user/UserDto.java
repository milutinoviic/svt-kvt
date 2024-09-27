package com.example.sitpass.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String surName;

    private String address;

    private String phoneNumber;

    private LocalDate birthday;

    private String city;

    private String email;

    private LocalDate createdAl;

    private String zipCode;

}
