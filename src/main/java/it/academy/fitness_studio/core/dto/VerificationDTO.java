package it.academy.fitness_studio.core.dto;

public class VerificationDTO {
    private String mail;
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
