package com.example.category_services.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CategoryResponseDto(Long id,
                                  String name,
                                  String description,
                                  Boolean active) {
}
