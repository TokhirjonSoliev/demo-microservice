package com.example.demo1.service;

import com.example.demo1.dto.UserCreateDto;
import com.example.demo1.dto.UserResponseDto;
import com.example.demo1.dto.UserUpdateDto;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);

    List<UserResponseDto> getAllUser();

    UserResponseDto editUser(UUID id, UserUpdateDto userUpdateDto);

    UserResponseDto getUser(UUID id);

    String deleteUser(UUID id);
}
