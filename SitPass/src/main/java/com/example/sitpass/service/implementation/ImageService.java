package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.image.ImageCreateFacilityDto;
import com.example.sitpass.dto.image.ImageCreateUserDto;
import com.example.sitpass.dto.image.ImageFacilityDto;
import com.example.sitpass.dto.image.ImageUserDto;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements com.example.sitpass.service.ImageService {


    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageUserDto createImageUser(ImageCreateUserDto imageDto, User user) throws UserNotFoundException {
        Image image = new Image();
        image.setPath(imageDto.getPath());
        if(user == null){
            throw new UserNotFoundException("Not found User");
        }
        image.setUser(user);
        imageRepository.save(image);
        ImageUserDto imageUserDto = convetToUserDto(image);
        return imageUserDto;
    }

    @Override
    public ImageFacilityDto createImageFacillty(ImageCreateFacilityDto imageDto, Facility facility) throws FacilityNotFoundException {
        Image image = new Image();
        image.setPath(imageDto.getPath());
        if(facility == null){
            throw new FacilityNotFoundException("Not found Facility");
        }
        image.setFacility(facility);
        imageRepository.save(image);
        ImageFacilityDto imageFacilityDto = convetFacilityImageToDto(image,image.getFacility());
        return imageFacilityDto;
    }

    @Override
    public Image createImage(String path, User user) {
        return null;
    }

    @Override
    public List<ImageUserDto> getAllImagesUser() {
        List<ImageUserDto> imagesDto = new ArrayList<>();
        for (Image image: imageRepository.findAll()){
            ImageUserDto imageUserDto = convetToUserDto(image);
            imagesDto.add(imageUserDto);
        }
        return imagesDto;
    }

    @Override
    public List<ImageFacilityDto> getAllImagesFacility() {
        List<ImageFacilityDto> imagesDto = new ArrayList<>();
        for (Image image: imageRepository.findAll()){
            ImageFacilityDto imageGetFacilltyDto = convetFacilityImageToDto(image,image.getFacility());
            imagesDto.add(imageGetFacilltyDto);
        }
        return imagesDto;
    }

    @Override
    public List<ImageFacilityDto> findByFacilityId(long facilityId) {
        List<ImageFacilityDto> imagesDto = new ArrayList<>();
        for (Image image: imageRepository.findAll()){
            if (image.getFacility().getId() == facilityId){
                ImageFacilityDto imgDto=convetFacilityImageToDto(image,image.getFacility());
                imagesDto.add(imgDto);
            }
        }
        return imagesDto;
    }

    @Override
    public Image convertDtoToImage(ImageCreateFacilityDto imageCreateFacilityDto, Facility facility) {
        Image image = new Image();
        image.setPath(imageCreateFacilityDto.getPath());
        image.setFacility(facility);

        return image;
    }

    @Override
    public ImageUserDto convetToUserDto(Image image) {
        ImageUserDto imageDto = new ImageUserDto();
        imageDto.setId(image.getId());
        imageDto.setPath(image.getPath());
        imageDto.setUserId(image.getUser().getId());
        return imageDto;
    }

    @Override
    public ImageFacilityDto convetFacilityImageToDto(Image image, Facility facility) {
        ImageFacilityDto imageDto = new ImageFacilityDto();
        imageDto.setId(image.getId());
        imageDto.setPath(image.getPath());
        imageDto.setFacilltyId(facility.getId());

        return imageDto;
    }

    @Override
    public Image getImageById(long id) {
       Image image = imageRepository.findById(id).orElse(null);
       return image;
    }

    @Override
    public ImageUserDto getImageUserByIdDto(long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if(image == null){

            return null;
        }

        ImageUserDto imageUserDto = convetToUserDto(image);
        return imageUserDto;

    }

    @Override
    public ImageFacilityDto getImageFacilityByIdDto(long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if(image == null){

            return null;
        }

        ImageFacilityDto imageFacilityDto = convetFacilityImageToDto(image,image.getFacility());
        return imageFacilityDto;

    }

    @Override
    public ImageUserDto updateImageUser(Long id, ImageCreateUserDto imageDetails) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            return null;
        }
        image.setPath(imageDetails.getPath());

        imageRepository.save(image);

        ImageUserDto imageUserDto = convetToUserDto(image);

        return imageUserDto;
    }

    @Override
    public ImageFacilityDto updateImageFacility(Long id, ImageCreateFacilityDto imageDetails) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            return null;
        }
        image.setPath(imageDetails.getPath());

        imageRepository.save(image);
        ImageFacilityDto imageFacilityDto = convetFacilityImageToDto(image,image.getFacility());


        return imageFacilityDto;
    }

    @Override
    public ImageUserDto deleteImageUser(long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            return null;
        }
        imageRepository.deleteById(id);
        ImageUserDto imageUserDto = convetToUserDto(image);

        return imageUserDto;
    }

    @Override
    public ImageFacilityDto deleteImageFacility(long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            return null;
        }
        imageRepository.deleteById(id);
        ImageFacilityDto imageGetUserDto = convetFacilityImageToDto(image,image.getFacility());

        return imageGetUserDto;
    }
}
