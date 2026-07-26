package com.example.Brand_services.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BrandRequestDto(@NotBlank(message = "Brand name cannot be blank")
                              String name,
                              @NotBlank(message = "Country cannot be blank")
                              String country,
                              String description,
                              Boolean active) {
}
