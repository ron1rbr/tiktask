package com.rbr.tiktask.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rbr.tiktask.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsCustomService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        return userRepository
                   .findWithRolesByEmail(email)
                   .map(UserDetailsCustom::new)
                   .orElseThrow(
                       () -> new UsernameNotFoundException("Invalid credentials")
                   );

    }

}
