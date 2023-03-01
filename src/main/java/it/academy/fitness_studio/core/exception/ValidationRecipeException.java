package it.academy.fitness_studio.core.exception;

public class ValidationRecipeException extends IllegalArgumentException {
    public ValidationRecipeException() {
    }

    public ValidationRecipeException(String message) {
        super(message);
    }

    public ValidationRecipeException(String message, Throwable cause) {
        super(message, cause);
    }
}
