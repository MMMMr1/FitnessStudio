package it.academy.fitness_studio.core.dto.user;

public class VerificationDTO {
//    todo нужно ли выносить в отдельное дто на контроллере?
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
