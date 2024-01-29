package com.ashish.blogappspringboot.security;

import com.ashish.blogappspringboot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtUtil jwtUtil;
    private UserService userService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JwtUtil jwtUtil,UserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.jwtAuthenticationFilter = new JwtAuthenticationFilter(new JwtAuthenticationManager(jwtUtil,userService));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
         http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").permitAll().antMatchers(HttpMethod.GET,"/articles/**").permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(((request, response, authException) -> {
             authException.printStackTrace();
         }));
         http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
    }

}
