package com.rbr.tiktask.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbr.tiktask.domain.user.Role;
import com.rbr.tiktask.domain.user.RoleName;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    
    Optional<Role> findByName(RoleName name);

}
