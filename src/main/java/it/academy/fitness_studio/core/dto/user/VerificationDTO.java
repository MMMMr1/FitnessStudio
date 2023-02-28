package it.academy.fitness_studio.core.dto.user;

import javax.validation.constraints.NotBlank;

public class VerificationDTO {
//    todo нужно ли выносить в отдельное дто на контроллере?
    @NotBlank(message = "Mail must not be blank")
    private String mail;
    @NotBlank(message = "Code must not be blank")
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
