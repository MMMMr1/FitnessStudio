package it.academy.fitness_studio.core.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException() {
    }

    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
