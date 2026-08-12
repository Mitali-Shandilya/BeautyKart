package com.example.productServices.service;




import com.example.productServices.dto.request.UserProfileUpdateDto;
import com.example.productServices.dto.request.UserRequestDto;
import com.example.productServices.dto.response.UserResponseDto;
import com.example.productServices.enums.Role;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    void deleteUser(Long userId);
    UserResponseDto updateUser(Long userId, UserProfileUpdateDto request, String requesterEmail);
    UserResponseDto updateUserRole(Long userId, Role role);

}