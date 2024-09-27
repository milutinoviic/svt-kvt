package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.manages.ManagesCreateDto;
import com.example.sitpass.dto.manages.ManagesDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Manages;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ManagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagesService implements com.example.sitpass.service.ManagesService {

    private final UserService userService;
    private final FacilityService facilityService;
    private final ManagesRepository managesRepository;


    @Autowired
    public ManagesService(UserService userService, FacilityService facilityService, ManagesRepository managesRepository) {
        this.userService = userService;
        this.facilityService = facilityService;
        this.managesRepository = managesRepository;
    }
    @Override
    public ManagesDto createManages(ManagesCreateDto managesDto) throws UserNotFoundException, FacilityNotFoundException {
        Manages manages = new Manages();
        manages.setStartData(LocalDate.now());
        manages.setEndData(LocalDate.now());
        User user = userService.getUserById(managesDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("Not found User");
        }
        manages.setUser(user);
        Facility facility = facilityService.getFacilityById(managesDto.getFacilltyId());
        if(facility == null){
            throw new FacilityNotFoundException("Not found Facility");
        }
        manages.setFacility(facility);
        managesRepository.save(manages);

        ManagesDto managesDto1 = convertToDto(manages);

        return managesDto1;

    }
    @Override
    public List<ManagesDto> getAllManages() {
        List<ManagesDto> managesDtoList = new ArrayList<>();
        for(Manages manages : managesRepository.findAll()){
            ManagesDto managesDto = convertToDto(manages);
            managesDtoList.add(managesDto);
        }
        return managesDtoList;
    }
    @Override
    public Manages getManagesById(long id) {
       Manages manages = managesRepository.findById(id).orElse(null);
       return manages;
    }
    @Override
    public ManagesDto getManagesByIdDto(long id) {
        Manages manages = managesRepository.findById(id).orElse(null);
        if(manages == null){

            return null;
        }
        ManagesDto managesDto = convertToDto(manages);
        return managesDto;

    }
    @Override
    public ManagesDto convertToDto(Manages manages){
        ManagesDto managesDto = new ManagesDto();
        managesDto.setId(manages.getId());
        managesDto.setStartData(manages.getStartData());
        managesDto.setEndData(manages.getEndData());
        UserDto userDto = userService.getUserByIdDto(manages.getUser().getId());
        managesDto.setUser(userDto);
        FacilityDto facilityDto = facilityService.getFacilityByIdDto(manages.getFacility().getId());
        managesDto.setFacillty(facilityDto);
        return managesDto;


    }
    @Override
    public ManagesDto updateManages(Long id, ManagesCreateDto managesDto) {
        Manages manages = managesRepository.findById(id).orElse(null);
        if (manages == null) {
            return null;
        }
        manages.setStartData(LocalDate.now());
        manages.setEndData(LocalDate.now());
        User user = userService.getUserById(managesDto.getUserId());
        manages.setUser(user);

        managesRepository.save(manages);

        ManagesDto managesDto1 = convertToDto(manages);


        return managesDto1;

    }
    @Override
    public ManagesDto deleteManages(long id) {
        Manages manages = managesRepository.findById(id).orElse(null);
        if (manages == null) {
            return null;
        }
        managesRepository.deleteById(id);
        ManagesDto managesDto = convertToDto(manages);
        return managesDto;

    }

    public boolean checkManagerExistsForFacility(Long facilityId) {
        return managesRepository.existsByFacilityId(facilityId);
    }

    public void removeManagerByFacilityId(Long facilityId) {
        managesRepository.deleteByFacilityId(facilityId);
    }


    public Long getFacilityIdForUser(Long userId) {
        Long facilityId = managesRepository.findFacilityIdByUserId(userId);
        return facilityId != null ? facilityId : 0;
    }

}
