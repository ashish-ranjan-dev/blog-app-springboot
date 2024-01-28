package com.ashish.blogappspringboot.controller;

import com.ashish.blogappspringboot.dtos.CreateUserDto;
import com.ashish.blogappspringboot.dtos.ErrorResponseDto;
import com.ashish.blogappspringboot.dtos.LoginUserDto;
import com.ashish.blogappspringboot.entities.UserEntity;
import com.ashish.blogappspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDto createUserDto){
        var user = userService.createUser(createUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserEntity> loginUser(@RequestBody LoginUserDto loginUserDto){
        var user = userService.loginUser(loginUserDto.getUsername(), loginUserDto.getPassword());

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UserService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        else{
            message = "Internal Server Error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new ErrorResponseDto(message),status);
    }

}
