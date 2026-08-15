package com.example.productServices.repository;

import com.example.productServices.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameIgnoreCase(String name);
    List<Product> findByActiveTrue();
    List<Product> findByBrandIdAndActiveTrue(Long brandId);
    List<Product> findByCategoryIdAndActiveTrue(Long categoryId);
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}