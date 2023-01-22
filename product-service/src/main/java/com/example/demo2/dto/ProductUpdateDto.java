package com.example.demo2.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String name;
    private String description;
    private BigDecimal price;
}
