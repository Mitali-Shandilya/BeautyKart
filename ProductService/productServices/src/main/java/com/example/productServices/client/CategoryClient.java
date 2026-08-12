package com.example.productServices.client;

import com.example.productServices.dto.response.CategoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "categoryServices",
        url = "http://localhost:8084"
)
public interface CategoryClient {
    @GetMapping("/api/categories/{id}")
    CategoryResponseDto getById(@PathVariable("id") Long id);
}
