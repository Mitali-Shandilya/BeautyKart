package com.example.OrderService.mapper;

import com.example.OrderService.dto.CartResponseDto;
import com.example.OrderService.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem toOrderItem(Long orderId, CartResponseDto cartItem) {
        OrderItem item = new OrderItem();
        item.setOrderId(orderId);
        item.setProductId(cartItem.productId());
        item.setProductName(cartItem.product().name());
        item.setImageUrl(cartItem.product().imageUrl());
        item.setQuantity(cartItem.quantity());
        item.setPrice(cartItem.product().price());
        return item;
    }
}
