package it.academy.fitness_studio.core.exception;

public class AuditNotFoundException extends RuntimeException {
    public AuditNotFoundException() {
    }
    public AuditNotFoundException(String message) {
        super(message);
    }
}
