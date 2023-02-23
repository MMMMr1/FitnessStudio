package it.academy.fitness_studio.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.CustomInstantConverter;
import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;

import java.time.Instant;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    @JsonProperty("uuid")
    private UUID uuid;

    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_create")
    private  Instant dtCreate;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_update")
    private  Instant dtUpdate;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("fio")
    private String name;
    @JsonProperty("role")
    private UserRole role;
    @JsonProperty("status")
    private UserStatus status;

    public UserModel() {
    }

    public UserModel(UUID uuid,
                     Instant dtCreate,
                     Instant dtUpdate,
                     String mail,
                     String fio,
                     UserRole role,
                     UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.name = fio;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}
