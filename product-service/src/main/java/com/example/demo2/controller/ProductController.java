package com.example.demo2.controller;

import com.example.demo2.dto.ProductCreateDto;
import com.example.demo2.dto.ProductResponseDto;
import com.example.demo2.dto.ProductUpdateDto;
import com.example.demo2.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productCreateDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductResponseDto> editProduct(@PathVariable String id, @RequestBody @Valid ProductUpdateDto productUpdateDto) {
        return ResponseEntity.ok(productService.editProduct(id, productUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
