package it.academy.fitness_studio.core.dto.user;

import it.academy.fitness_studio.core.validator.ValidEmail;
import it.academy.fitness_studio.core.validator.ValidPassword;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {
    @ValidEmail
    private String mail;
    @ValidPassword
    private String password;
    public UserLoginDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
    public UserLoginDTO() {
    }
    public String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
}
