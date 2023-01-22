package com.example.demo1.mapper;

import com.example.demo1.dto.UserCreateDto;
import com.example.demo1.dto.UserResponseDto;
import com.example.demo1.dto.UserUpdateDto;
import com.example.demo1.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User UserCreateDtoToUser(UserCreateDto userCreateDto);

    UserResponseDto UserToUserResponseDto(User user);

    void UserUpdateDtoToExistUser(UserUpdateDto userUpdateDto, @MappingTarget User user);
}
