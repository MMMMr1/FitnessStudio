package it.academy.fitness_studio.core.dto.user;


import it.academy.fitness_studio.core.validator.ValidEmail;
import it.academy.fitness_studio.core.validator.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserRegistrationDTO {
    @ValidEmail
    private String mail;
    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp="([A-Za-z]+) ([A-Za-z]+)|([А-Яа-я]+ [А-Яа-я]+)",
            message = "Write correct full name")
    private String fio;
    @ValidPassword
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
