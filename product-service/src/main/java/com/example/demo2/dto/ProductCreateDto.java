package com.example.demo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    @NotBlank
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
}
