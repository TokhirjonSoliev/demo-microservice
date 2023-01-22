package com.example.demo1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String firstName;
    private String lastName;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String username;
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String password;
}
