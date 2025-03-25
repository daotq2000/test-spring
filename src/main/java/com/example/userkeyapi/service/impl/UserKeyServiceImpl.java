package com.example.userkeyapi.service.impl;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;
import com.example.userkeyapi.entity.UserEntity;
import com.example.userkeyapi.exception.InvalidException;
import com.example.userkeyapi.exception.NotFoundException;
import com.example.userkeyapi.repository.UserKeyRepository;
import com.example.userkeyapi.service.UserKeyService;
import com.example.userkeyapi.service.mapper.UserEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserKeyServiceImpl implements UserKeyService {
    private final UserKeyRepository repository;
    private final UserEntityMapper userEntityMapper;

    public UserKeyServiceImpl(UserKeyRepository repository, UserEntityMapper userEntityMapper) {
        this.repository = repository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUserKey(UserCreateRequest userCreateRequest) {
        List<UserEntity> existingKeys = repository.findByUsernameOrderByCreatedDateAsc(userCreateRequest.getUsername());
        validateMessage(userCreateRequest, existingKeys);
        String uniqueCode = generateUniqueCode(userCreateRequest.getUsername());
        UserEntity newUser = new UserEntity(userCreateRequest.getUsername(),uniqueCode,LocalDateTime.now());
        var userEntitySave = repository.save(newUser);
        return userEntityMapper.toTarget(userEntitySave);
    }

    private void validateMessage(UserCreateRequest userCreateRequest, List<UserEntity> existingKeys) {
        if (userCreateRequest.getUsername() == null || userCreateRequest.getUsername().isEmpty()) {
            throw new InvalidException("Username cannot be empty");
        }
        if (existingKeys.size() >= 5) {
            throw new InvalidException("User already has 5 keys");
        }
    }

    private String generateUniqueCode(String username) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        return Base64.getEncoder().encodeToString((username + "#" + randomNum).getBytes());
    }


    @Override
    public List<User> getUserKeys(String username) {
        if(StringUtils.hasLength(username)){
           return userEntityMapper.toTarget(repository.findByUsernameOrderByCreatedDateAsc(username));
        }
        return userEntityMapper.toTarget(repository.findAll());
    }

    @Override
    public void deleteUserKey(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Key not found");
        }
        repository.deleteById(id);
    }
}