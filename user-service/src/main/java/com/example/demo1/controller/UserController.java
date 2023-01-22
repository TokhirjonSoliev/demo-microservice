package com.example.demo1.controller;

import com.example.demo1.dto.UserCreateDto;
import com.example.demo1.dto.UserResponseDto;
import com.example.demo1.dto.UserUpdateDto;
import com.example.demo1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// should add logging and swagger

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String id){
        return ResponseEntity.ok(userService.getUser(UUID.fromString(id)));
    }

    @PostMapping("/new")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody @Valid UserCreateDto userCreateDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserResponseDto> editUser(@PathVariable String id, @RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.editUser(UUID.fromString(id), userUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return ResponseEntity.ok(userService.deleteUser(UUID.fromString(id)));
    }
}
