package com.example.Brand_services.repository;

import com.example.Brand_services.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByActiveTrue();
}
