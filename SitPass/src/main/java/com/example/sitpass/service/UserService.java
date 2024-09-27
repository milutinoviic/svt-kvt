package com.example.sitpass.service;


import com.example.sitpass.dto.user.UserCreateDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.model.User;

import java.util.List;

public interface UserService {

    UserCreateDto createUser(UserCreateDto userDto) throws EmailExistsException;

    List<UserDto> getAllUsers();

    UserDto getUserByIdDto(long id);

    User getUserById(long id);

    UserDto convertToDto(User user);

    User updateUser(Long id, UserCreateDto userDetails);

    UserDto deleteUser(Long id);
}
