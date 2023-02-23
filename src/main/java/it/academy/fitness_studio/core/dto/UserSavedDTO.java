package it.academy.fitness_studio.core.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.CustomInstantConverter;
import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;

import java.time.Instant;
import java.util.UUID;

public class UserSavedDTO {
    UUID uuid;

    private Instant dtCreate;

    private  Instant dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;

//    public UserSavedDTO(UUID uuid,
//                        String mail,
//                        String fio,
//                        UserRole role,
//                        UserStatus status,
//                        String password) {
//        this.uuid = uuid;
//        this.mail = mail;
//        this.fio = fio;
//        this.role = role;
//        this.status = status;
//        this.password = password;
//    }

    public UserSavedDTO(String mail,
                        String fio,
                        UserRole role,
                        UserStatus status,
                        String password) {

        uuid = UUID.randomUUID();
        dtCreate = Instant.now();
        dtUpdate = dtCreate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }
//
//    public UserSavedDTO(String mail, String fio, String password) {
//        uuid = UUID.randomUUID();
//        dtCreate = Instant.now();
//        dtUpdate = dtCreate;
//        this.mail = mail;
//        this.fio = fio;
//        this.role = UserRole.USER;
//        this.status = UserStatus.WAITING_ACTIVATION;
//        this.password = password;
//    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }
}
