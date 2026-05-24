package com.rbr.tiktask.security.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rbr.tiktask.domain.user.Role;
import com.rbr.tiktask.domain.user.UserStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsCustom implements UserDetails {
    
    private final com.rbr.tiktask.domain.user.User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return user
                   .getRoles()
                   .stream()
                   .map(Role::getName)
                   .map(Enum::name)
                   .map(SimpleGrantedAuthority::new)
                   .toList();

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
