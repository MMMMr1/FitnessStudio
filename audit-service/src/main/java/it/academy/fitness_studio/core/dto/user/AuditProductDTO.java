package it.academy.fitness_studio.core.dto.user;

public class AuditProductDTO {
    private String role;
    private String mail;


    private String text;
//    Создана запись в журнале питания
//    Описание действия пользователя
    private String id;
    private String type;

//    id той сущности с которой производили действия

    public AuditProductDTO() {
    }

    public AuditProductDTO(String role, String mail, String text, String id, String type, String uuid, String fio) {
        this.role = role;
        this.mail = mail;
        this.text = text;
        this.id = id;
        this.type = type;
    }



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
