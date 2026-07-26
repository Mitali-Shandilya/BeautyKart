package com.example.productServices.dto.response;

public record ProductResponseDto(Long id,
                                 String name,
                                 String description,
                                 Double price,
                                 Integer quantity,
                                 String imageUrl,
                                 Boolean active,
                                 CategoryResponseDto category,
                                 BrandResponseDto brand) {
}
