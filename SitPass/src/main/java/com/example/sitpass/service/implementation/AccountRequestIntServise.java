package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.accountRequest.AccountRequestCreateDto;
import com.example.sitpass.dto.accountRequest.AccountRequestDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.model.AccountRequest;
import com.example.sitpass.repository.AccountRequestRepository;
import com.example.sitpass.repository.UserRepository;
import com.example.sitpass.service.AccountRequestInt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestIntServise implements AccountRequestInt {


    private final AccountRequestRepository accountRequestRepository;
    private final UserRepository userRepository;

    public AccountRequestIntServise(AccountRequestRepository accountRequestRepository, UserRepository userRepository) {
        this.accountRequestRepository = accountRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AccountRequestDto createAccountRequest(AccountRequestCreateDto accountRequestCreateDto) throws  EmailExistsException {

        if(userRepository.existsUserByEmail(accountRequestCreateDto.getEmail()) || accountRequestRepository.existsAccountRequestByEmail(accountRequestCreateDto.getEmail())) {
            throw new EmailExistsException("Email aleady exist");
        }


        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setEmail(accountRequestCreateDto.getEmail());
        accountRequest.setPassword(accountRequestCreateDto.getPassword());
        accountRequest.setCreatedAt(LocalDate.now());
        accountRequest.setAddress(accountRequestCreateDto.getAddress());
        accountRequest.setStatus(accountRequestCreateDto.getStatus());
        accountRequest.setRejectionReason(accountRequestCreateDto.getRejectionReason());

        accountRequestRepository.save(accountRequest);

        return convertToDto(accountRequest);



    }

    @Override
    public AccountRequestDto convertToDto(AccountRequest accountRequest) {

        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setId(accountRequest.getId());
        accountRequestDto.setEmail(accountRequest.getEmail());
        accountRequestDto.setPassword(accountRequest.getPassword());
        accountRequestDto.setCreatedAt(accountRequest.getCreatedAt());
        accountRequestDto.setAddress(accountRequest.getAddress());
        accountRequestDto.setStatus(accountRequest.getStatus());
        accountRequestDto.setRejectionReason(accountRequest.getRejectionReason());

        return accountRequestDto;

    }

    @Override
    public AccountRequestDto deleteAccountRequest(Long id) {
        AccountRequest accountRequest = accountRequestRepository.findById(id).orElse(null);
        if(accountRequest == null) {
            return null;
        }
        accountRequestRepository.delete(accountRequest);
        return convertToDto(accountRequest);


    }

    @Override
    public AccountRequestDto updateAccountRequest(Long id,AccountRequestCreateDto accountRequestCreateDto) {
        AccountRequest accountRequest = accountRequestRepository.findById(id).orElse(null);
        if(accountRequest == null) {
            return null;
        }
        accountRequest.setEmail(accountRequestCreateDto.getEmail());
        accountRequest.setPassword(accountRequestCreateDto.getPassword());
        accountRequest.setCreatedAt(LocalDate.now());
        accountRequest.setAddress(accountRequestCreateDto.getAddress());
        accountRequest.setStatus(accountRequestCreateDto.getStatus());
        accountRequest.setRejectionReason(accountRequestCreateDto.getRejectionReason());

        accountRequestRepository.save(accountRequest);

        return convertToDto(accountRequest);

    }

    @Override
    public AccountRequestDto getByIdAccountRequest(Long id) {
        Optional<AccountRequest> accountRequest = accountRequestRepository.findById(id);
        if(accountRequest.isEmpty()) {
            return null;
        }
        return convertToDto(accountRequest.get());
    }

    @Override
    public List<AccountRequestDto> getAll() {
        List<AccountRequestDto> accountRequestsDto = new ArrayList<>();
            for(AccountRequest accountRequest : accountRequestRepository.findPendingOrRejectedRequests()) {
                accountRequestsDto.add(convertToDto(accountRequest));
            }
            return accountRequestsDto;
    }


}
