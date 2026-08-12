package com.example.OrderService.controller;

import com.example.OrderService.dto.OrderResponseDto;
import com.example.OrderService.dto.UpdateOrderStatusDto;
import com.example.OrderService.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    //place order
    @PostMapping("/user/{userId}")
    public OrderResponseDto placeOrder(@PathVariable Long userId){
        return orderService.placeOrder(userId);
    }

    //get orders by orderId
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderByOrderId(@PathVariable Long orderId){
        return orderService.getOrderByOrderId(orderId);
    }

    //update order status
    @PutMapping("/{orderId}/status")
    public OrderResponseDto updateOrderStatus(@PathVariable Long orderId, @RequestBody @Valid UpdateOrderStatusDto request){
        return orderService.updateOrderStatus(orderId,request.orderStatus());
    }

     //get order by userId
    @GetMapping("/user/{userId}")
    public List<OrderResponseDto> getOrdersByUserId(@PathVariable Long userId){
        return orderService.getOrdersByUserId(userId);
    }
    
    @GetMapping("/my-orders")
    public List<OrderResponseDto> getMyOrders(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    //ADMIN
    //get all orders for admin
    @GetMapping("/admin")
    public List<OrderResponseDto> getAllOrders() {
            return orderService.getAllOrders();
        }
}
