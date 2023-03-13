package it.academy.fitness_studio.core.dto.user;


import it.academy.fitness_studio.core.enums.UserRole;
import it.academy.fitness_studio.core.enums.UserStatus;
import it.academy.fitness_studio.core.validator.*;

public class UserDTO {
    @ValidEmail(message="Wrong format")
    private String mail;
    @ValidName(message = "Wrong format")
    private String fio;
    @ValueOfEnum(enumClass = UserRole.class)
    private String role;
    @ValueOfEnum(enumClass = UserStatus.class)
    private String status;
    @ValidPassword
    private String password;
    public UserDTO(String mail,
                   String fio,
                   String role,
                   String status,
                   String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }
    public UserDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.role = UserRole.USER.toString();
        this.status = UserStatus.WAITING_ACTIVATION.toString();
    }
    public UserDTO() {
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
        return UserRole.valueOf(role);
    }

    public void setRole(String role) {
        this.role = role;
    }
    public UserStatus getStatus() {
        return UserStatus.valueOf(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}