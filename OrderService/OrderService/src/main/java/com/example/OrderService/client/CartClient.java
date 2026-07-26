package com.example.OrderService.client;

import com.example.OrderService.dto.CartResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "cartServices",
        url = "http://localhost:8086"
)
public interface CartClient {
    @GetMapping("/api/cart/user/{userId}")
    List<CartResponseDto> getCartByUserId(@PathVariable("userId") Long userId);

    @DeleteMapping("/api/cart/user/{userId}")
    ResponseEntity<Void> deleteAllItems(@PathVariable Long userId);
}
