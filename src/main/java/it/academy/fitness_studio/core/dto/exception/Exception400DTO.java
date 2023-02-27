package it.academy.fitness_studio.core.dto.exception;

public class Exception400DTO {
    private String logref;
    private String message;

    public Exception400DTO() {
    }

    public Exception400DTO(String message) {
        this.logref = "error";
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}
