package com.example.cartServices.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartRequestDto(

        @NotNull(message="user Id ca not be null")
        Long userId,

        @NotNull(message="product Id ca not be null")
        Long productId,

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity should be more than 0")
        Integer quantity) {
}
