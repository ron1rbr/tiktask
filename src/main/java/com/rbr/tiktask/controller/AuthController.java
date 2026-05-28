package com.rbr.tiktask.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbr.tiktask.dto.auth.AuthResponse;
import com.rbr.tiktask.dto.auth.LoginRequest;
import com.rbr.tiktask.dto.auth.RegisterRequest;
import com.rbr.tiktask.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/register")
    AuthResponse register(@Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);

    }

}
