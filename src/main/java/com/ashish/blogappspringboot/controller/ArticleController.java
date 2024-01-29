package com.ashish.blogappspringboot.controller;

import com.ashish.blogappspringboot.entities.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    @GetMapping(value = "")
    public String getArticles(){
        return "get all articles";
    }

    @PostMapping(value = "")
    public String getUser(@AuthenticationPrincipal UserEntity user){
        return user.getUsername();
    }
}
