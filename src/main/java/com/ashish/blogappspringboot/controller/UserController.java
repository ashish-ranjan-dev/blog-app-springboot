package com.ashish.blogappspringboot.controller;

import com.ashish.blogappspringboot.dtos.CreateUserDto;
import com.ashish.blogappspringboot.dtos.ErrorResponseDto;
import com.ashish.blogappspringboot.dtos.LoginUserDto;
import com.ashish.blogappspringboot.entities.UserEntity;
import com.ashish.blogappspringboot.response.UserResponse;
import com.ashish.blogappspringboot.security.JwtUtil;
import com.ashish.blogappspringboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(value = "")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserDto createUserDto){
        var user = userService.createUser(createUserDto);
        var userResponse = mapper.map(user,UserResponse.class);
        userResponse.setToken(jwtUtil.createJwt(user.getId()));
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserDto loginUserDto){
        var user = userService.loginUser(loginUserDto.getUsername(), loginUserDto.getPassword());
        var userResponse = mapper.map(user,UserResponse.class);
        userResponse.setToken(jwtUtil.createJwt(user.getId()));
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @ExceptionHandler({
            UserService.UserNotFoundException.class,
            UserService.InvalidCredentialsException.class
    })
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UserService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }
        else if (ex instanceof UserService.InvalidCredentialsException){
            message = ex.getMessage();
            status = HttpStatus.FORBIDDEN;
        }
        else{
            message = "Internal Server Error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(new ErrorResponseDto(message),status);
    }

}
