package com.ashish.blogappspringboot.respositories;

import com.ashish.blogappspringboot.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
