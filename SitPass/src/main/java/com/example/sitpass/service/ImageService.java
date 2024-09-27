package com.example.sitpass.service;

import com.example.sitpass.dto.image.ImageCreateFacilityDto;
import com.example.sitpass.dto.image.ImageCreateUserDto;
import com.example.sitpass.dto.image.ImageFacilityDto;
import com.example.sitpass.dto.image.ImageUserDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;
import java.util.List;


public interface ImageService {
    ImageUserDto createImageUser(ImageCreateUserDto imageDto, User user) throws UserNotFoundException;

    ImageFacilityDto createImageFacillty(ImageCreateFacilityDto imageDto, Facility facility) throws FacilityNotFoundException;

    Image createImage(String path, User user);

    List<ImageUserDto> getAllImagesUser();

    List<ImageFacilityDto> getAllImagesFacility();

    List<ImageFacilityDto> findByFacilityId(long facilityId);

    Image convertDtoToImage(ImageCreateFacilityDto imageCreateFacilityDto, Facility facility);

    ImageUserDto convetToUserDto(Image image);

    ImageFacilityDto convetFacilityImageToDto(Image image, Facility facility);

    Image getImageById(long id);

    ImageUserDto getImageUserByIdDto(long id);

    ImageFacilityDto getImageFacilityByIdDto(long id);

    ImageUserDto updateImageUser(Long id, ImageCreateUserDto imageDetails);

    ImageFacilityDto updateImageFacility(Long id, ImageCreateFacilityDto imageDetails);

    ImageUserDto deleteImageUser(long id);

    ImageFacilityDto deleteImageFacility(long id);
}
