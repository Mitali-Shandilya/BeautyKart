package com.example.productServices.dto.auth;

public record AuthResponseDto(String token,
                              String tokenType,
                              Long userId,
                              String firstName,
                              String lastName,
                              String email,
                              String role) {
}
