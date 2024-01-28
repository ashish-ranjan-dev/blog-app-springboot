package com.ashish.blogappspringboot.user;

import com.ashish.blogappspringboot.entities.UserEntity;
import com.ashish.blogappspringboot.respositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void can_create_user(){
        var user = UserEntity.builder().username("ashish135ranjan").email("ashishranjan@gmail.com").build();
        userRepository.save(user);
    }

    @Test
    void can_find_user(){
        var user = UserEntity.builder().username("ashish135ranjan").email("ashishranjan@gmail.com").build();
        userRepository.save(user);
        Assertions.assertEquals(1,userRepository.findAll().size());
    }
}
