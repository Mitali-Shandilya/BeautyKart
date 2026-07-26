package com.example.cartServices.mapper;

import com.example.cartServices.dto.CartRequestDto;
import com.example.cartServices.dto.CartResponseDto;
import com.example.cartServices.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    //entity to dto
//    public CartResponseDto toDto(CartItem cart){
//        return new CartResponseDto(
//                cart.getId(),
//                cart.getUserId(),
//                cart.getProductId(),
//                cart.getQuantity(),
//
//        );
//    }

    //dto to entity
    public CartItem toEntity(CartRequestDto requestDto){
        CartItem cartItem=new CartItem();
        cartItem.setUserId(requestDto.userId());
        cartItem.setProductId(requestDto.productId());
        cartItem.setQuantity(requestDto.quantity());
        return cartItem;
    }
}
