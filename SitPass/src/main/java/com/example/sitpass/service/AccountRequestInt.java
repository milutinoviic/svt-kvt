package com.example.sitpass.service;

import com.example.sitpass.dto.accountRequest.AccountRequestCreateDto;
import com.example.sitpass.dto.accountRequest.AccountRequestDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.model.AccountRequest;

import java.util.List;

public interface AccountRequestInt {

    public AccountRequestDto createAccountRequest(AccountRequestCreateDto accountRequestCreateDto) throws EmailExistsException;

    public AccountRequestDto convertToDto(AccountRequest accountRequest);

    public AccountRequestDto deleteAccountRequest(Long id);

    public AccountRequestDto updateAccountRequest(Long id,AccountRequestCreateDto accountRequestCreateDto);

    public AccountRequestDto getByIdAccountRequest(Long id);

    public List<AccountRequestDto> getAll();


}
