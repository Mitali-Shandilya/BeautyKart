package com.example.productServices.dto.request;

import com.example.productServices.enums.Role;
import jakarta.validation.constraints.NotNull;

public record UserRoleUpdateDto(
        @NotNull(message = "Role is required")
        Role role
) {
}
