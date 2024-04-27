package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Product; 

// JPA: java persistence API
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByProductName(String productName);
}
