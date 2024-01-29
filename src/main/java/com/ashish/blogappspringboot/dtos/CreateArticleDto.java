package com.ashish.blogappspringboot.dtos;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class CreateArticleDto {
    @NonNull
    private String title;

    @Nullable
    private String subTitle;

    @NonNull
    private String body;
}
