package com.example.userkeyapi.service.mapper;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;
import com.example.userkeyapi.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper extends ModelMapper<UserEntity, User> {

    UserEntity toUserEntity(UserCreateRequest user);
}
