package com.example.cartServices.dto;

public record CartResponseDto(Long id,
                              Long userId,
                              Long productId,
                              Integer quantity,
                              ProductDto product,
                              Double totalPrice) {
}
