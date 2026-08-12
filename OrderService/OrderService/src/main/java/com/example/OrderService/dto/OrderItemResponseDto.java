package com.example.OrderService.dto;

public record OrderItemResponseDto(
        Long productId,
        String productName,
        String imageUrl,
        Integer quantity,
        Double price,
        Double subTotal
) {
}
