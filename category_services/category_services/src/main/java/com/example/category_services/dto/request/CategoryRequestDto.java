package com.example.category_services.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(@NotBlank(message = "Category name cannot be blank")
                                 String name,
                                 String description,
                                 Boolean active) {
}
