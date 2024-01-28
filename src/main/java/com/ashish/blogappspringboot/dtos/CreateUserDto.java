package com.ashish.blogappspringboot.dtos;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    private String username;


    private String email;


    private String password;
}
