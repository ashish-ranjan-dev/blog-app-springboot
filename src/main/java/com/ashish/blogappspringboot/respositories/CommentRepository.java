package com.ashish.blogappspringboot.respositories;

import com.ashish.blogappspringboot.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
