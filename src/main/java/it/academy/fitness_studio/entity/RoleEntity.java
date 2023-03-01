package it.academy.fitness_studio.entity;

import it.academy.fitness_studio.core.UserRole;

import javax.persistence.*;

@Entity
@Table(schema = "app",name = "role")
public class RoleEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private UserRole role;
    public RoleEntity() {
    }
    public RoleEntity(UserRole role) {
        this.role = role;
    }
    public UserRole getRole() {
        return role;
    }
}
