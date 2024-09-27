package com.example.sitpass.service;

import com.example.sitpass.dto.manages.ManagesCreateDto;
import com.example.sitpass.dto.manages.ManagesDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Manages;

import java.util.List;

public interface ManagesService {

    ManagesDto createManages(ManagesCreateDto managesDto) throws UserNotFoundException, FacilityNotFoundException;

    List<ManagesDto> getAllManages();

    Manages getManagesById(long id);

    ManagesDto getManagesByIdDto(long id);

    ManagesDto convertToDto(Manages manages);

    ManagesDto updateManages(Long id, ManagesCreateDto managesDto);

    ManagesDto deleteManages(long id);
}
