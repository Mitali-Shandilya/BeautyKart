package com.example.productServices.repository;

import com.example.productServices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(String name);
    List<Product> findByBrandId(Long brandId);
    List<Product> findByCategoryId(Long categoryId);
}