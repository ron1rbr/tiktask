package com.rbr.tiktask.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rbr.tiktask.domain.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findWithRolesByEmail(String email);

}
