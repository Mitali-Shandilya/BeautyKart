package com.example.OrderService.dto;

import com.example.OrderService.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusDto(
        @NotNull(message = "Order status is required")
        OrderStatus orderStatus
) {
}
