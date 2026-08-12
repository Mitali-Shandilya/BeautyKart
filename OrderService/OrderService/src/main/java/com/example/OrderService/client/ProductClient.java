package com.example.OrderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.OrderService.dto.ProductDto;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8083"
)
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    ProductDto getById(@PathVariable Long id);

    @PutMapping("/api/products/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id,@RequestParam Integer quantity);
}