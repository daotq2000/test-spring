package com.example.userkeyapi.service.mapper;

import com.example.userkeyapi.controller.request.UserCreateRequest;
import com.example.userkeyapi.dto.User;
import com.example.userkeyapi.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T18:26:55+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.26 (Ubuntu)"
)
@Component
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public UserEntity toSource(User target) {
        if ( target == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( target.getId() );
        userEntity.setUsername( target.getUsername() );
        userEntity.setCode( target.getCode() );
        userEntity.setCreatedDate( target.getCreatedDate() );

        return userEntity;
    }

    @Override
    public User toTarget(UserEntity source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setUsername( source.getUsername() );
        user.setCode( source.getCode() );
        user.setCreatedDate( source.getCreatedDate() );

        return user;
    }

    @Override
    public List<UserEntity> toSource(List<User> target) {
        if ( target == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( target.size() );
        for ( User user : target ) {
            list.add( toSource( user ) );
        }

        return list;
    }

    @Override
    public List<User> toTarget(List<UserEntity> source) {
        if ( source == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( source.size() );
        for ( UserEntity userEntity : source ) {
            list.add( toTarget( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity toUserEntity(UserCreateRequest user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( user.getUsername() );

        return userEntity;
    }
}
