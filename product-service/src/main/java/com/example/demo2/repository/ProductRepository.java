package com.example.demo2.repository;

import com.example.demo2.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    Optional<Product> findById(String id);
}
