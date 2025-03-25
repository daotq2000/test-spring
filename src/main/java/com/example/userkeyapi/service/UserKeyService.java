package com.example.userkeyapi.service;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;

import java.util.List;

public interface UserKeyService {
    User createUserKey(UserCreateRequest userCreateRequest);
    List<User> getUserKeys(String username);
    void deleteUserKey(Long id);
}