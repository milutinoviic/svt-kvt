package com.example.sitpass.controller;

import com.example.sitpass.dto.user.UserCreateDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.EmailExistsException;
import com.example.sitpass.model.User;
import com.example.sitpass.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }



    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserByIdDto(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/possibleManages")
    public ResponseEntity<List<UserDto>> findUsersNotInManages() {
        List<UserDto> users = userService.findUsersNotInManages();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserCreateDto> createUser(@Valid @RequestBody UserCreateDto userDto){
           try {
               UserCreateDto createdUserDto = userService.createUser(userDto);
               return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
           }catch (EmailExistsException e){
               return new ResponseEntity<>(HttpStatus.CONFLICT);
           }

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserCreateDto userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            UserDto updatedUserDto = userService.convertToDto(updatedUser);
            return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        UserDto userDto =userService.deleteUser(id);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/verify-password/{id}")
    public ResponseEntity<Boolean> checkUserPassword(@PathVariable Long id, @RequestBody String password) {
        boolean isPasswordValid = userService.checkPassword(id, password);

        if (isPasswordValid) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }



}
