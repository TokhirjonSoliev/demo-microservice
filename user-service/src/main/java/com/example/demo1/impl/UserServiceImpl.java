package com.example.demo1.impl;

import com.example.demo1.dto.UserCreateDto;
import com.example.demo1.dto.UserResponseDto;
import com.example.demo1.dto.UserUpdateDto;
import com.example.demo1.entity.User;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.UserService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        User user = userMapper.UserCreateDtoToUser(userCreateDto);
        userRepository.save(user);
        return userMapper.UserToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::UserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto editUser(UUID id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("User not found with this id = ") + id));
        userMapper.UserUpdateDtoToExistUser(userUpdateDto, user);
        userRepository.save(user);
        return userMapper.UserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("User not found with this id = ") + id));
        return userMapper.UserToUserResponseDto(user);
    }

    @Override
    public String deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(("User not found with this id = ") + id));
        userRepository.delete(user);
        return "User deleted";
    }
}
