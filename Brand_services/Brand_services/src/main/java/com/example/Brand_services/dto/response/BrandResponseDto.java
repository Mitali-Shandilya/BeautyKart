package com.example.Brand_services.dto.response;

import jakarta.persistence.Column;

public record BrandResponseDto(Long id,
                            String name,
                            String country,
                            String description,
                            Boolean active) {
                            }
