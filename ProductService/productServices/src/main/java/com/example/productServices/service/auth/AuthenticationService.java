package com.example.productServices.service.auth;

import com.example.productServices.dto.auth.AuthResponseDto;
import com.example.productServices.dto.auth.LoginRequestDto;
import com.example.productServices.dto.auth.RegisterRequestDto;

public interface AuthenticationService {

    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);

}
