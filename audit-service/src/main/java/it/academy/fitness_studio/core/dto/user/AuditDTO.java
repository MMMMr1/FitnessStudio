package it.academy.fitness_studio.core.dto.user;

import it.academy.fitness_studio.core.enums.TypeOfAudit;
import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.validator.ValidEmail;
import it.academy.fitness_studio.core.validator.ValidName;
import it.academy.fitness_studio.core.validator.ValueOfEnum;

import java.time.Instant;
import java.util.UUID;

public class AuditDTO {
    private String role;
    private String mail;


    private String text;
//    Создана запись в журнале питания
//    Описание действия пользователя
    private String id;
    private String type;
    private String uuid;
    private String fio;
//    id той сущности с которой производили действия

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
