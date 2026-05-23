package com.rbr.tiktask.service;

import java.util.UUID;

import com.rbr.tiktask.domain.user.User;

public interface UserService {
    
    User create(
        String name,
        String email,
        String password
    );

    User getById(UUID id);

    User getByEmail(String email);

}
