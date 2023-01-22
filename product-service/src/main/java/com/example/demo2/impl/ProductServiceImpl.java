package com.example.demo2.impl;

import com.example.demo2.dto.ProductCreateDto;
import com.example.demo2.dto.ProductResponseDto;
import com.example.demo2.dto.ProductUpdateDto;
import com.example.demo2.mapper.ProductMapper;
import com.example.demo2.model.Product;
import com.example.demo2.repository.ProductRepository;
import com.example.demo2.service.ProductService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductResponseDto createProduct(ProductCreateDto productCreateDto) {
        Product product = productMapper.productCreateDtoToProduct(productCreateDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto editProduct(String id, ProductUpdateDto productUpdateDto) {
        Product product = getProduct(id);
        productMapper.ProductUpdateDtoToExistProduct(productUpdateDto, product);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        Product product = getProduct(id);
        return productMapper.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteProduct(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
        return "product deleted";
    }

    private Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found"));
    }
}
