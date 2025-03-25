package com.example.userkeyapi.repository;

import com.example.userkeyapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeyRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsernameOrderByCreatedDateAsc(String username);
}