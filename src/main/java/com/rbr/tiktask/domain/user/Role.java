package com.rbr.tiktask.domain.user;

import com.rbr.tiktask.domain.base.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends AuditableEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;
    
}
