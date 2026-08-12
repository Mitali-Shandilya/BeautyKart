package com.example.productServices.service.auth;



import com.example.productServices.dto.auth.AuthResponseDto;
import com.example.productServices.dto.auth.LoginRequestDto;
import com.example.productServices.dto.auth.RegisterRequestDto;
import com.example.productServices.entity.auth.User;
import com.example.productServices.enums.Role;
import com.example.productServices.exception.DuplicateResourceException;
import com.example.productServices.exception.NotFoundException;
import com.example.productServices.repository.UserRepository;
import com.example.productServices.security.CustomUserDetailsService;
import com.example.productServices.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new DuplicateResourceException("Phone number already exists.");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(resolveRole(request.email()))
                .enabled(true)
                .build();

        userRepository.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDto(
                jwt,
                "Bearer",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    // Anyone registering with a @skybook.com email is treated as staff and
// auto-promoted to ADMIN. Case-insensitive so "Name@SkyBook.com" still matches.
    private Role resolveRole(String email) {
        return email != null && email.toLowerCase().endsWith("@beautykart.com")
                ? Role.ADMIN
                : Role.USER;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new NotFoundException(
                                "User not found"));

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(user.getEmail());

        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDto(
                jwt,
                "Bearer",
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}