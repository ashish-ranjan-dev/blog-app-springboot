package com.ashish.blogappspringboot.service;

import com.ashish.blogappspringboot.dtos.CreateUserDto;
import com.ashish.blogappspringboot.entities.UserEntity;
import com.ashish.blogappspringboot.respositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(ModelMapper mapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserDto createUserDto){
        var user = mapper.map(createUserDto,UserEntity.class);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
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
        var passMatch = passwordEncoder.matches(password,user.getPassword());
        if(!passMatch){
            throw new InvalidCredentialsException();
        }
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

    public static class InvalidCredentialsException extends IllegalArgumentException{
        public InvalidCredentialsException(){
            super("Invalid username or password combination");
        }
    }
}
