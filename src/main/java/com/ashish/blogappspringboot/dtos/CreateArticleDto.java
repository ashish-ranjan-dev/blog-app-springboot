package com.ashish.blogappspringboot.dtos;

import jakarta.persistence.Column;
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
