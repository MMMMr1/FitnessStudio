package it.academy.fitness_studio.core.dto.audit;

import it.academy.fitness_studio.core.enums.AuditType;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.validator.ValidEmail;
import it.academy.fitness_studio.core.validator.ValidName;
import it.academy.fitness_studio.core.validator.ValueOfEnum;

import javax.validation.constraints.NotBlank;

public class AuditDTO {
    @ValueOfEnum(enumClass = UserRole.class)
    private String role;
    @ValidEmail
    private String mail;
    @NotBlank
    private String text;
    @NotBlank
    private String id;
    @ValueOfEnum(enumClass = AuditType.class)
    private String type;
    @NotBlank
    private String uuid;
    @ValidName
    private String fio;
    public AuditDTO() {
    }

    public AuditDTO(String role, String mail, String text, String id, String type, String uuid, String fio) {
        this.role = role;
        this.mail = mail;
        this.text = text;
        this.id = id;
        this.type = type;
        this.uuid = uuid;
        this.fio = fio;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
