package com.example.cartServices.dto;

public record ProductDto(Long id,
                         String name,
                         Double price,
                         Integer quantity,
                         String imageUrl
                        ) {
}
