package it.academy.fitness_studio.entity;


import it.academy.fitness_studio.core.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "app",name = "role")
public class RoleEntity implements Serializable {
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
