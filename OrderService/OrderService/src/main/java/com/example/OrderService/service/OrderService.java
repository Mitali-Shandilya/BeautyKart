package com.example.OrderService.service;
import com.example.OrderService.client.CartClient;
import com.example.OrderService.dto.CartResponseDto;
import com.example.OrderService.dto.OrderItemResponseDto;
import com.example.OrderService.dto.OrderResponseDto;
import com.example.OrderService.entity.Order;
import com.example.OrderService.entity.OrderItem;
import com.example.OrderService.enums.OrderStatus;
import com.example.OrderService.exception.NotFoundException;
import com.example.OrderService.mapper.OrderItemMapper;
import com.example.OrderService.mapper.OrderMapper;
import com.example.OrderService.repository.OrderItemRepository;
import com.example.OrderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartClient cartClient;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    //placing an order
    @Transactional
    public OrderResponseDto placeOrder(Long userId){
        List<CartResponseDto> cartItems;

        try {
            cartItems = cartClient.getCartByUserId(userId);
        }
        catch (Exception ex) {
            throw new NotFoundException(
                    "No cart items found for user id " + userId
            );
        }

        Double totalAmount=cartItems.stream().mapToDouble(CartResponseDto::totalPrice).sum();

        Order order=Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .orderStatus(OrderStatus.PLACED)
                .orderDate(LocalDateTime.now())
                .build();

        Order savedOrder=orderRepository.save(order);

        List<OrderItem> savedItems = cartItems.stream()
                .map(item -> orderItemMapper.toOrderItem(
                        savedOrder.getId(),
                        item
                ))
                .map(orderItemRepository::save)
                .toList();

        cartClient.deleteAllItems(userId);

        List<OrderItemResponseDto> itemDtos=cartItems.stream()
                .map(item->new OrderItemResponseDto(
                        item.productId(),
                        item.product().name(),
                        item.quantity(),
                        item.product().price(),
                        item.totalPrice()
                )).toList();

        return new OrderResponseDto(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getTotalAmount(),
                savedOrder.getOrderStatus(),
                savedOrder.getOrderDate(),
                itemDtos
        );
    }

    //get orders by orderId
    public OrderResponseDto getOrderByOrderId(Long orderId){
        Order order=orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found by id "+orderId));
        List<OrderItem> items=orderItemRepository.findByOrderId(orderId);

        return orderMapper.toOrderResponseDto(order,items);
    }

    //get orders by userId
    public List<OrderResponseDto> getOrdersByUserId(Long userId){
        List<Order> orders=orderRepository.findByUserId(userId);

        if(orders.isEmpty()){
            throw new NotFoundException("No orders found for this user with userId "+userId);
        }

        return orders.stream().map(order ->{
            List<OrderItem> items=orderItemRepository.findByOrderId(order.getId());

            return orderMapper.toOrderResponseDto(order,items);
        }).toList();
    }

    //update the status
    public OrderResponseDto updateOrderStatus(Long orderId,OrderStatus status){
        Order order=orderRepository.findById(orderId).orElseThrow(()->new NotFoundException("Order not found with id "+orderId));
        order.setOrderStatus(status);
        Order updatedOrder=orderRepository.save(order);
        List<OrderItem> items=orderItemRepository.findByOrderId(orderId);
        return orderMapper.toOrderResponseDto(updatedOrder,items);
    }
}
