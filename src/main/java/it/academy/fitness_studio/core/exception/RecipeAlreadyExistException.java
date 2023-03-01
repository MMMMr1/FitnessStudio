package it.academy.fitness_studio.core.exception;

public class RecipeAlreadyExistException extends RuntimeException {
    public RecipeAlreadyExistException() {
    }

    public RecipeAlreadyExistException(String message) {
        super(message);
    }
}
