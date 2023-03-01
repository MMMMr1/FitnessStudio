package it.academy.fitness_studio.core.dto.error;

public class ExceptionStructuredDTO {
    private String field;
    private String message;

    public ExceptionStructuredDTO() {
    }

    public ExceptionStructuredDTO(String message) {
        this.field = "structured_error";
        this.message = message;
    }

    public ExceptionStructuredDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
