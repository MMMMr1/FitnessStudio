package it.academy.fitness_studio.core.dto;

import it.academy.fitness_studio.core.validator.ValidEmail;

import javax.validation.constraints.NotBlank;

public class VerificationMailDTO {
    @ValidEmail
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public VerificationMailDTO(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public VerificationMailDTO() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
