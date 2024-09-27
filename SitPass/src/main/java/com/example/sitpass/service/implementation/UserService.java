package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.user.UserCreateDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.Role;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.RoleRepository;
import com.example.sitpass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements com.example.sitpass.service.UserService {

    private final UserRepository userRepository;
    private ImageService imageService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(ImageService imageService, UserRepository userRepository,RoleRepository roleRepository) {
        this.imageService = imageService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserCreateDto createUser(UserCreateDto userDto) throws EmailExistsException {

        if(userRepository.existsUserByEmail(userDto.getEmail())) {
            throw new EmailExistsException("Email aleady exist");
        }


        User user = new User();
        user.setName(userDto.getName());
        user.setSurName(userDto.getSurName());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setBirthday(userDto.getBirthday());
        user.setCity(userDto.getCity());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreatedAl(LocalDate.now());
        user.setZipCode(userDto.getZipCode());
        Image image = imageService.createImage(userDto.getImagePath(),user);
        user.setImage(image);

        Role role = roleRepository.findByName("USER");

        if (role == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }

        // Dodajte ulogu korisniku
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);



        // Sačuvajte korisnika
        userRepository.save(user);

        return userDto;



    }
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> usersGetDtos= new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserDto userDto = convertToDto(user);
            usersGetDtos.add(userDto);
        }
        return usersGetDtos;
    }
    @Override
    public UserDto getUserByIdDto(long id) {

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return null;
        }

        UserDto userDto = convertToDto(user.get());
        return userDto;
    }
    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return null;
        }
        return user.get();
    }


    @Override
    public UserDto convertToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurName(user.getSurName());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setBirthday(user.getBirthday());
        userDto.setCity(user.getCity());
        userDto.setEmail(user.getEmail());
        userDto.setCreatedAl(user.getCreatedAl());
        userDto.setZipCode(user.getZipCode());

        return userDto;

    }

    @Override
    public User updateUser(Long id, UserCreateDto userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }


        user.setName(userDetails.getName());
        user.setSurName(userDetails.getSurName());
        user.setAddress(userDetails.getAddress());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setBirthday(userDetails.getBirthday());
        user.setCity(userDetails.getCity());
        user.setEmail(userDetails.getEmail());
        user.setZipCode(userDetails.getZipCode());

        if(userDetails.getPassword() == null || userDetails.getPassword().isEmpty() ){

            user.setPassword(user.getPassword());
        }else{
            user.setPassword(userDetails.getPassword());
        }

        userRepository.save(user);

        return user;

    }


    public List<UserDto> findUsersNotInManages() {

        List<UserDto> usersGetDtos= new ArrayList<>();
        for (User user : userRepository.findUsersNotInManages()) {
            UserDto userDto = convertToDto(user);
            usersGetDtos.add(userDto);
        }
        return usersGetDtos;

    }



    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        userRepository.delete(user);
        UserDto userDto = convertToDto(user);
        return userDto;
    }
    public boolean checkPassword(Long id,String password){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return false;
        }

        return user.getPassword().equals(password);

    }



}
