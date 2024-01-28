package com.ashish.blogappspringboot.service;

import com.ashish.blogappspringboot.dtos.CreateUserDto;
import com.ashish.blogappspringboot.entities.UserEntity;
import com.ashish.blogappspringboot.respositories.UserRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserDto createUserDto){
        var user = UserEntity.builder()
                .username(createUserDto.getUsername())
                .email(createUserDto.getEmail())
//                .password(password) // TODO: add password
        .build();

        userRepository.save(user);
        return user;

    }

    public UserEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    public UserEntity loginUser(String username,String password){
        var user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        //TODO: check password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("user not found with username :"+username);
        }

        public UserNotFoundException(Long id){
            super("user not found with id :"+id);
        }
    }
}
