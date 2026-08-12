package com.example.productServices.service;


import com.example.productServices.dto.request.UserProfileUpdateDto;
import com.example.productServices.dto.request.UserRequestDto;
import com.example.productServices.dto.response.UserResponseDto;
import com.example.productServices.entity.auth.User;
import com.example.productServices.enums.Role;
import com.example.productServices.exception.AccessDeniedException;
import com.example.productServices.exception.DuplicateResourceException;
import com.example.productServices.exception.NotFoundException;
import com.example.productServices.mapper.UserMapper;
import com.example.productServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already exists.");
        }
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        User user = UserMapper.fromRequestDto(request);
        // fromRequestDto stores the raw password as-is — encode it here
        // before persisting, same as AuthenticationServiceImpl.register does.
        user.setPassword(passwordEncoder.encode(request.password()));

        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toResponseDto).toList();
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + userId));
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserProfileUpdateDto request, String requesterEmail) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + userId));

        if (!user.getEmail().equals(requesterEmail)) {
            throw new AccessDeniedException("You can only update your own profile.");
        }

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phoneNumber());

        User updatedUser = userRepository.save(user);
        return UserMapper.toResponseDto(updatedUser);
    }

    @Override
    public UserResponseDto updateUserRole(Long userId, Role role) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + userId));

        user.setRole(role);

        User updatedUser = userRepository.save(user);
        return UserMapper.toResponseDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + userId));
        userRepository.delete(user);
    }
}
