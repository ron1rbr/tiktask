package com.rbr.tiktask.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.rbr.tiktask.domain.user.User;
import com.rbr.tiktask.dto.auth.AuthResponse;
import com.rbr.tiktask.dto.auth.LoginRequest;
import com.rbr.tiktask.dto.auth.RegisterRequest;
import com.rbr.tiktask.security.jwt.JwtService;
import com.rbr.tiktask.service.AuthService;
import com.rbr.tiktask.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = userService
                        .create(
                            request.name(),
                            request.email(),
                            request.password()
                        );

        return new AuthResponse(jwtService.generate(user.getEmail()));

    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        return new AuthResponse(jwtService.generate(request.email()));
        
    }

}
