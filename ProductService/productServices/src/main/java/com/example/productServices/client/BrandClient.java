package com.example.productServices.client;

import com.example.productServices.dto.response.BrandResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "BrandServices",
        url = "http://localhost:8085"
)
public interface BrandClient {
    @GetMapping("/api/brands/{id}")
    BrandResponseDto getById(@PathVariable Long id);
}
