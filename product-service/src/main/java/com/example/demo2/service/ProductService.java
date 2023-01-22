package com.example.demo2.service;

import com.example.demo2.dto.ProductCreateDto;
import com.example.demo2.dto.ProductResponseDto;
import com.example.demo2.dto.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDto createProduct(ProductCreateDto productCreateDto);

    ProductResponseDto editProduct(String id, ProductUpdateDto productCreateDto);

    ProductResponseDto getProductById(String id);

    List<ProductResponseDto> getAllProduct();

    String deleteProduct(String id);
}
