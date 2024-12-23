package com.example.userService.Service;

import com.example.userService.entity.UserEntity;

import java.util.List;

public interface UserServices {

    UserEntity saveUser(UserEntity user);

    UserEntity getUserById(String userId);

    List<UserEntity> getAllUser();
}
