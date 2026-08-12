package com.example.productServices.controller;

import com.example.productServices.dto.auth.AuthResponseDto;
import com.example.productServices.dto.auth.LoginRequestDto;
import com.example.productServices.dto.auth.RegisterRequestDto;
import com.example.productServices.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDto register(
            @Valid @RequestBody RegisterRequestDto request) {

        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(
            @Valid @RequestBody LoginRequestDto request) {

        return authenticationService.login(request);
    }
}