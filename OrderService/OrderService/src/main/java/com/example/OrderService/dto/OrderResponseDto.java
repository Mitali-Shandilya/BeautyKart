package com.example.OrderService.dto;

import com.example.OrderService.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long orderId,
        Long userId,
        Double totalAmount,
        OrderStatus status,
        LocalDateTime orderDate,
        List<OrderItemResponseDto> items
) {
}
