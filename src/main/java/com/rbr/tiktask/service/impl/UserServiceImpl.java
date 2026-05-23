package com.rbr.tiktask.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbr.tiktask.domain.user.Role;
import com.rbr.tiktask.domain.user.RoleName;
import com.rbr.tiktask.domain.user.User;
import com.rbr.tiktask.domain.user.UserStatus;
import com.rbr.tiktask.exception.DuplicateResourceException;
import com.rbr.tiktask.exception.ResourceNotFoundException;
import com.rbr.tiktask.repository.RoleRepository;
import com.rbr.tiktask.repository.UserRepository;
import com.rbr.tiktask.service.UserService;
import com.rbr.tiktask.util.EmailNormalizer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User create(String name, String email, String password) {
        
        String normalized = EmailNormalizer.normalize(email);

        if (userRepository.existsByEmail(normalized)) {
            throw new DuplicateResourceException("Email already exists");
        }

        Role role = roleRepository
                        .findByName(RoleName.ROLE_USER)
                        .orElseThrow(
                            () -> new ResourceNotFoundException("Default role not found")
                        );
                    
        User user = new User();
        user.setName(name.trim());
        user.setEmail(normalized);
        user.setPassword(password);
        user.setStatus(UserStatus.PENDING_VERIFICATION);
        user.getRoles().add(role);

        return userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {

        return userRepository
                   .findById(id)
                   .orElseThrow(
                       () -> new ResourceNotFoundException("User not found")
                   );

    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        
        String normalized = EmailNormalizer.normalize(email);

        return userRepository
                   .findWithRolesByEmail(normalized)
                   .orElseThrow(
                       () -> new ResourceNotFoundException("User not found")
                   );

    }

}
