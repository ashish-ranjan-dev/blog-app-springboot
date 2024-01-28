package com.ashish.blogappspringboot.security;

import org.springframework.security.web.authentication.AuthenticationFilter;


public class JwtAuthenticationFilter extends AuthenticationFilter {
    public JwtAuthenticationFilter(JwtAuthenticationConverter authenticationConverter,JwtAuthenticationManager authenticationManager){
        super(authenticationManager,authenticationConverter);

    }
}
