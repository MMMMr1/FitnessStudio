package it.academy.fitness_studio.core.dto.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.converter.jackson.CustomInstantConverter;
import it.academy.fitness_studio.core.enums.TypeOfAudit;

import java.time.Instant;
import java.util.UUID;

public class AuditModel {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_create")
    private Instant dtCreate;
    @JsonProperty("user")
    private ActorModel auditorModel;
    @JsonProperty("text")
    private String text;
    @JsonProperty("type")
    private TypeOfAudit type;
    @JsonProperty("id")
    private String id;

    public AuditModel() {
    }

    public AuditModel(UUID uuid, Instant dtCreate, ActorModel auditorModel, String text, TypeOfAudit type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.auditorModel = auditorModel;
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

    public ActorModel getAuditModel() {
        return auditorModel;
    }

    public void setAuditorModel(ActorModel auditModel) {
        this.auditorModel = auditModel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TypeOfAudit getType() {
        return type;
    }

    public void setType(TypeOfAudit type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
