package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.recipe.RecipeDTO;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;

public interface ValidatorRecipe {
    void validate(RecipeDTO recipe) throws ValidationRecipeException;
}
