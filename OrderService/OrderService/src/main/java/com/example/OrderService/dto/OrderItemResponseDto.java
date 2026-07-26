package com.example.OrderService.dto;

public record OrderItemResponseDto(
        Long productId,
        String productName,
        Integer quantity,
        Double price,
        Double subTotal
) {
}
