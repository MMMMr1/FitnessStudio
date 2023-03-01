package it.academy.fitness_studio.core.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException() {
    }

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
