package com.example.cartServices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cartServices.dto.ProductDto;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8083"
)
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductDto getById(@PathVariable("id") Long id);
}