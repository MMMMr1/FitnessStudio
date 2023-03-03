package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.product.RecipeDTO;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;

public interface IValidatorRecipe {
    void validate(RecipeDTO recipe) throws ValidationRecipeException;
}
