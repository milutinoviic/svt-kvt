package com.example.sitpass.controller;

import com.example.sitpass.dto.image.ImageCreateFacilityDto;
import com.example.sitpass.dto.image.ImageCreateUserDto;
import com.example.sitpass.dto.image.ImageFacilityDto;
import com.example.sitpass.dto.image.ImageUserDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.User;
import com.example.sitpass.service.implementation.FacilityService;
import com.example.sitpass.service.implementation.ImageService;
import com.example.sitpass.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final FacilityService facilityService;
    private  UserService userService;

    @Autowired
    public ImageController(ImageService imageService, UserService userService, FacilityService facilityService) {
        this.imageService = imageService;
        this.userService = userService;
        this.facilityService = facilityService;
    }

    @GetMapping("/usersImages")
    public ResponseEntity<List<ImageUserDto>> getAllImagesUser() {
        List<ImageUserDto> imagesDto = imageService.getAllImagesUser();
        return new ResponseEntity<>(imagesDto, HttpStatus.OK);
    }

    @GetMapping("/facilltyImages")
    public ResponseEntity<List<ImageFacilityDto>> getAllImagesFacillty() {
        List<ImageFacilityDto> imagesDto = imageService.getAllImagesFacility();
        return new ResponseEntity<>(imagesDto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ImageUserDto> getUserImageById(@PathVariable long id) {
        ImageUserDto imageDto = imageService.getImageUserByIdDto(id);
        if (imageDto != null) {
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/facillty/{id}")
    public ResponseEntity<ImageFacilityDto> getFacilityImageById(@PathVariable long id) {
        ImageFacilityDto imageDto = imageService.getImageFacilityByIdDto(id);
        if (imageDto != null) {
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createImgUser")
    public ResponseEntity<ImageUserDto> createImageUser(@Valid @RequestBody ImageCreateUserDto image) throws UserNotFoundException {
        try {
            User user = userService.getUserById(image.getUserId());
            ImageUserDto createdImage = imageService.createImageUser(image, user);
            return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
        }catch (UserNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/facility/{id}")
    public ResponseEntity<ImageFacilityDto> createImageFacillty(@PathVariable Long facilityId, @Valid @RequestBody ImageCreateFacilityDto image) throws FacilityNotFoundException {
        Facility facility = facilityService.getFacilityById(facilityId);
        ImageFacilityDto createdImage = imageService.createImageFacillty(image, facility);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @PutMapping("us/{id}")
    public ResponseEntity<ImageUserDto> updateImageUser(@PathVariable Long id, @RequestBody ImageCreateUserDto image) {
        ImageUserDto updatedImageDto = imageService.updateImageUser(id, image);
        return new ResponseEntity<>(updatedImageDto, HttpStatus.OK);
    }


    @PutMapping("fa/{id}")
    public ResponseEntity<ImageFacilityDto> updateImageFacillty(@PathVariable Long id, @RequestBody ImageCreateFacilityDto image) {
        ImageFacilityDto updatedImageDto = imageService.updateImageFacility(id, image);
        return new ResponseEntity<>(updatedImageDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ImageUserDto> deleteImageUser(@PathVariable long id) {
        ImageUserDto deletedImageDto = imageService.deleteImageUser(id);
        return new ResponseEntity<>(deletedImageDto, HttpStatus.OK);
    }

    @DeleteMapping("/facillty/{id}")
    public ResponseEntity<ImageFacilityDto> deleteImageFacillty(@PathVariable long id) {
        ImageFacilityDto deletedImageDto = imageService.deleteImageFacility(id);
        if (deletedImageDto != null) {
            return new ResponseEntity<>(deletedImageDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
