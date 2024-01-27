package com.ashish.blogappspringboot.respositories;

import com.ashish.blogappspringboot.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity,Long> {
}
