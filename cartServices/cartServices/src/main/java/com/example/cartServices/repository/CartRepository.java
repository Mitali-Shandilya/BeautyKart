package com.example.cartServices.repository;

import com.example.cartServices.dto.CartResponseDto;
import com.example.cartServices.entity.CartItem;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId,Long productId);
}
