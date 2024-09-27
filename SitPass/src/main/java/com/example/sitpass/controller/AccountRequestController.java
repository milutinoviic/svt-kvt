package com.example.sitpass.controller;

import com.example.sitpass.dto.accountRequest.AccountRequestCreateDto;
import com.example.sitpass.dto.accountRequest.AccountRequestDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.service.implementation.AccountRequestIntServise;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountRequest")
public class AccountRequestController {

    private final AccountRequestIntServise accountRequestIntServise;

    public AccountRequestController(AccountRequestIntServise accountRequestIntServise) {
        this.accountRequestIntServise = accountRequestIntServise;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRequestDto> getAccountRequestById(@PathVariable Long id) {
        AccountRequestDto accountRequestDto = accountRequestIntServise.getByIdAccountRequest(id);
        if (accountRequestDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountRequestDto, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<AccountRequestDto>> getAllAccountRequests() {
        List<AccountRequestDto> accountRequestDtos=accountRequestIntServise.getAll();
        return new ResponseEntity<>(accountRequestDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountRequestDto> createAccountRequest(@Valid @RequestBody AccountRequestCreateDto accountRequestCreateDto) throws EmailExistsException {
        try {
            AccountRequestDto accountRequestDto = accountRequestIntServise.createAccountRequest(accountRequestCreateDto);
            return new ResponseEntity<>(accountRequestDto, HttpStatus.CREATED);

        }catch (EmailExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);


        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRequestDto> updateAccountRequest(@PathVariable Long id ,@Valid @RequestBody AccountRequestCreateDto accountRequestCreateDto) {
        AccountRequestDto accountRequestDto = accountRequestIntServise.updateAccountRequest(id,accountRequestCreateDto);
        return new ResponseEntity<>(accountRequestDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountRequestDto> deleteAccountRequest(@PathVariable Long id) {
        AccountRequestDto accountRequestDto = accountRequestIntServise.deleteAccountRequest(id);
        if (accountRequestDto != null) {
            return new ResponseEntity<>(accountRequestDto , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
