package it.academy.fitness_studio.core.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
