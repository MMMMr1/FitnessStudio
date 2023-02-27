package it.academy.fitness_studio.core.exception;

public class ValidationProductException extends IllegalArgumentException {
    public ValidationProductException() {
    }

    public ValidationProductException(String message) {
        super(message);
    }
}
