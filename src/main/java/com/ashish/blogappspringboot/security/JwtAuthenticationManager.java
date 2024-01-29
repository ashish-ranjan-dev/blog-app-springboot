package com.ashish.blogappspringboot.security;

import com.ashish.blogappspringboot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationManager(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JwtAuthentication){
            var jwtAuthentication = (JwtAuthentication) authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtUtil.retrieveUserId(jwt);
            var userEntity = userService.getUser(userId);
            jwtAuthentication.userEntity = userEntity;
            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;
        }

        throw new IllegalAccessError("Cannot authenticate user with non-JWT authentication");
    }
}
