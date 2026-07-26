package com.example.cartServices.service;

import com.example.cartServices.client.ProductClient;
import com.example.cartServices.dto.CartRequestDto;
import com.example.cartServices.dto.CartResponseDto;
import com.example.cartServices.dto.ProductDto;
import com.example.cartServices.entity.CartItem;
import com.example.cartServices.exception.NotFoundException;
import com.example.cartServices.mapper.CartMapper;
import com.example.cartServices.repository.CartRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductClient productClient;

    //add an item to cart
    public CartResponseDto addItem(CartRequestDto request){
        System.out.println("Product Id = " + request.productId());
        ProductDto product =productClient.getById(request.productId());
        CartItem newItem =cartMapper.toEntity(request);
        CartItem savedItem =cartRepository.save(newItem);
        return new CartResponseDto(
        savedItem.getId(),
        savedItem.getUserId(),
        savedItem.getProductId(),
        savedItem.getQuantity(),
        product,
        product.price() * savedItem.getQuantity()
        );
    }

    //get cart items by user id
    public List<CartResponseDto> findCartByUserId(Long id){
        List<CartItem> existingCartItems=cartRepository.findByUserId(id);
        if(existingCartItems.isEmpty()){
            throw new NotFoundException("No cart items found for user id " + id);
        }
        return existingCartItems.stream().map(items->{
            ProductDto product=productClient.getById(items.getProductId());
            Double totalPrice=product.price()*items.getQuantity();
            return new CartResponseDto(items.getId(), items.getUserId(),items.getProductId(),items.getQuantity(),product,totalPrice);
        }).toList();
    }

    //update the quantity of items in cart
    public CartResponseDto updateCartItems(Long id, CartRequestDto requestDto){
        CartItem exisitingCartItem=cartRepository.findById(id).orElseThrow(()->new NotFoundException("cart item not found with id:"+id));
        exisitingCartItem.setQuantity(requestDto.quantity());
        CartItem savedCartItem=cartRepository.save(exisitingCartItem);
        ProductDto product=productClient.getById(savedCartItem.getProductId());
        Double totalPrice=product.price()*requestDto.quantity();
        return new  CartResponseDto(
                savedCartItem.getId(),
                savedCartItem.getUserId(),
                savedCartItem.getProductId(),
                savedCartItem.getQuantity(),
                product,
                totalPrice
        );
    }

    //delete a cart item by id
    public void deleteItemInCart(Long cartItemId){
        CartItem existingItem=cartRepository.findById(cartItemId).orElseThrow(()->new NotFoundException("cart item not found with id "+cartItemId));
        cartRepository.delete(existingItem);
    }

    //clear cart
    public void clearCart(Long userId){
        List<CartItem> cartItems =
                cartRepository.findByUserId(userId);
        if(cartItems.isEmpty()){
            throw new NotFoundException(
                    "No cart items found for user id " + userId
            );
        }
        cartRepository.deleteAll(cartItems);
    }
}
