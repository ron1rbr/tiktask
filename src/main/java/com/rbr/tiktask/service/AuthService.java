package com.rbr.tiktask.service;

import com.rbr.tiktask.dto.auth.AuthResponse;
import com.rbr.tiktask.dto.auth.LoginRequest;
import com.rbr.tiktask.dto.auth.RegisterRequest;

public interface AuthService {
    
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
