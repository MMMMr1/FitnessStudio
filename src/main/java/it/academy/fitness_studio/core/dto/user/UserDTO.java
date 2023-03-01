package it.academy.fitness_studio.core.dto.user;


import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;
import it.academy.fitness_studio.core.validator.ValidEmail;
import it.academy.fitness_studio.core.validator.ValidPassword;
import it.academy.fitness_studio.core.validator.ValueOfEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {
    @ValidEmail(message="Wrong format of mail")
    private String mail;
    @Pattern(regexp="([A-Za-z]+) ([A-Za-z]+)|([А-Яа-я]+ [А-Яа-я]+)",
    message = "Wrong format of name")
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //    public UserRole getRole() {
//        return role;
//    }
//
//    public void setRole(UserRole role) {
//        this.role = role;
//    }

    public String getStatus() {
        return status;
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
