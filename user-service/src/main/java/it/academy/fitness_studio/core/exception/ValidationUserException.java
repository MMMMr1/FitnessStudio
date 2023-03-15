package it.academy.fitness_studio.core.exception;

public class ValidationUserException extends IllegalArgumentException {
    public ValidationUserException() {
    }
    public ValidationUserException(String message) {
        super(message);
    }
}
