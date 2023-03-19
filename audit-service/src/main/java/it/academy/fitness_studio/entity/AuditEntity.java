package it.academy.fitness_studio.entity;

import it.academy.fitness_studio.core.enums.AuditType;
import it.academy.fitness_studio.core.enums.UserRole;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity
@Table(schema = "app",name = "audit")

public class AuditEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "dtcreate")
    private Instant dtCreate;
    @Column(name = "uuid_user")
    private String uuid;
    @Column(name = "mail_user")
    private String mail;
    @Column(name = "fio_user")
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_user")
    private UserRole role;
    @Column(name = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_audit")
    private AuditType type;
    @Column(name = "id_modified")
    private String id_modified;

    public AuditEntity() {
    }
    public AuditEntity(String uuid, String mail, String fio, UserRole role, String text, AuditType type, String id_modified) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.id_modified = id_modified;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Instant getDtCreate() {
        return dtCreate;
    }
    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
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
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
    public String getId_modified() {
        return id_modified;
    }
    public void setId_modified(String id_modified) {
        this.id_modified = id_modified;
    }
}
