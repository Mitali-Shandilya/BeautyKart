package com.example.cartServices.repository;

import com.example.cartServices.dto.CartResponseDto;
import com.example.cartServices.entity.CartItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
}
