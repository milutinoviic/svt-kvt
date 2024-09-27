package com.example.sitpass.dto.accountRequest;

import com.example.sitpass.model.RequestStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountRequestDto {

    private Long id;

    private String email;

    private String password;

    private LocalDate createdAt;

    private String address;

    private RequestStatus status;

    private String rejectionReason;

}
