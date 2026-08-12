package com.example.productServices.mapper;

import com.example.productServices.dto.auth.RegisterRequestDto;
import com.example.productServices.dto.request.UserRequestDto;
import com.example.productServices.dto.response.UserResponseDto;
import com.example.productServices.entity.auth.User;
import com.example.productServices.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private UserMapper(){

    }

    public static User fromRequestDto(UserRequestDto dto) {

        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .role(Role.USER)
                .enabled(true)
                .build();
    }

    public static UserResponseDto toResponseDto(User user){
        return  new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}