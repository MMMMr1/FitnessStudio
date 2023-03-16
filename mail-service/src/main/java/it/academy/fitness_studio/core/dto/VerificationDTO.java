package it.academy.fitness_studio.core.dto;

import javax.validation.constraints.NotBlank;

public class VerificationDTO {
    @NotBlank(message = "Must not be blank")
    private String mail;
    @NotBlank(message = "Must not be blank")
    private String code;
    public VerificationDTO() {
    }
    public VerificationDTO(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }
    public String getMail() {
        return mail;
    }
    public String getCode() {
        return code;
    }
}
