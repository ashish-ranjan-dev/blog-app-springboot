package com.ashish.blogappspringboot.security;

import com.ashish.blogappspringboot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    private JwtUtil jwtUtil;
    private UserService userService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JwtUtil jwtUtil,UserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(new JwtAuthenticationConverter(), new JwtAuthenticationManager(jwtUtil,userService));
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
         http.cors().and().csrf().disable().authorizeRequests().requestMatchers(HttpMethod.POST,"/users","/users/login").permitAll().anyRequest().authenticated();
         http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
         return http.build();
    }

}
