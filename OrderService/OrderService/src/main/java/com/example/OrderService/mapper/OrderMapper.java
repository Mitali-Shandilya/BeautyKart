package com.example.OrderService.mapper;

import com.example.OrderService.dto.OrderItemResponseDto;
import com.example.OrderService.dto.OrderResponseDto;
import com.example.OrderService.entity.Order;
import com.example.OrderService.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    //entity to dto
    public OrderItemResponseDto toOrderItemResponseDto(OrderItem item){
        return new OrderItemResponseDto(
                item.getProductId(),
                item.getProductName(),
                item.getQuantity(),
                item.getPrice(),
                item.getPrice()* item.getQuantity()
        );
    }

    public OrderResponseDto toOrderResponseDto(Order order, List<OrderItem> items){
        List<OrderItemResponseDto> itemDtos=items.stream()
                .map(this::toOrderItemResponseDto)
                .toList();
        return new OrderResponseDto(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getOrderDate(),
                itemDtos
        );
    }
}
