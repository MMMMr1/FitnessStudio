package it.academy.fitness_studio.entity;

import it.academy.fitness_studio.core.enums.UserStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "app",name = "status")
public class StatusEntity implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    public StatusEntity() {
    }
    public StatusEntity(UserStatus status) {
        this.status = status;
    }
    public UserStatus getStatus() {
        return status;
    }

}
