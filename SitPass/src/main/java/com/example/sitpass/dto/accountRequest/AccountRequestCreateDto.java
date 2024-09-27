package com.example.sitpass.dto.accountRequest;

import com.example.sitpass.model.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountRequestCreateDto {

    @NotNull
    private String email;

    @NotNull
    private String password;


    @NotNull
    private String address;

    @NotNull
    private RequestStatus status;

    @NotNull
    private String rejectionReason;

}
