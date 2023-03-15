package it.academy.fitness_studio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "app",name = "authentication")
public class AuthenticationEntity {
    @Id
    private String mail;
    @Column(name = "code")
    private String code;

    public AuthenticationEntity(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }

    public AuthenticationEntity() {
    }
    public String getMail() {
        return mail;
    }
    public String getCode() {
        return code;
    }
}
