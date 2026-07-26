package com.example.cartServices.controller;

import com.example.cartServices.dto.CartRequestDto;
import com.example.cartServices.dto.CartResponseDto;
import com.example.cartServices.entity.CartItem;
import com.example.cartServices.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    //posting a cart item
    @PostMapping
    public CartResponseDto create(@Valid @RequestBody CartRequestDto request){
        System.out.println(request.productId());
        return cartService.addItem(request);
    }

    //get cart items of a user by user id
    @GetMapping("/user/{userId}")
    public List<CartResponseDto> getByUserId(@PathVariable Long userId){
        return cartService.findCartByUserId(userId);
    }

    //update cart items by id
    @PutMapping("/{cartItemId}")
    public CartResponseDto updateCart(@PathVariable Long cartItemId,@Valid @RequestBody CartRequestDto requestDto){
        return  cartService.updateCartItems(cartItemId, requestDto);
    }

    //delete a cart item by id
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId){
        cartService.deleteItemInCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    //clear cart
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteAllItems(@PathVariable Long userId){
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
