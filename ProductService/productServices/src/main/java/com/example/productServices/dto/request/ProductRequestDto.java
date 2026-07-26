package com.example.productServices.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequestDto(@NotBlank(message = "Product name cannot be blank")
                                String name,
                                String description,
                                @Positive(message = "Price must be greater than 0")
                                Double price,
                                @Min(value = 0, message = "Quantity cannot be negative")
                                Integer quantity,
                                String imageUrl,
                                Long categoryId,
                                Long brandId,
                                Boolean active) {
}

