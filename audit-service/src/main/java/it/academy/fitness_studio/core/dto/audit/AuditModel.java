package it.academy.fitness_studio.core.dto.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.converter.jackson.CustomInstantConverter;
import it.academy.fitness_studio.core.dto.user.UserDetailsDTO;
import it.academy.fitness_studio.core.enums.AuditType;

import java.time.Instant;
import java.util.UUID;
@JsonIgnoreProperties
public class AuditModel {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_create")
    private Instant dtCreate;
    @JsonProperty("user")
    private UserDetailsDTO userDetails;
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private AuditType type;
    @JsonProperty("id")
    private String id;

    public AuditModel() {
    }

    public AuditModel(UUID uuid, Instant dtCreate, UserDetailsDTO userDetails, String text, AuditType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.userDetails = userDetails;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public UserDetailsDTO getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDTO userDetails) {
        this.userDetails = userDetails;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AuditType getType() {
        return type;
    }

    public void setType(AuditType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
