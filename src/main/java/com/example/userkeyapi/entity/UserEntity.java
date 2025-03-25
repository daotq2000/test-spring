package com.example.userkeyapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",length = 150,nullable = false)
    private String username;
    @Column(name = "code",nullable = false,unique = true)
    private String code;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public UserEntity(String username, String code, LocalDateTime createdDate) {
        this.username = username;
        this.code = code;
        this.createdDate = createdDate;
    }
}