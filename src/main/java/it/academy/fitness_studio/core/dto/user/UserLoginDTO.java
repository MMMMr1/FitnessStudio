package it.academy.fitness_studio.core.dto.user;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {
    @NotBlank
    private String mail;
    @NotBlank
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
