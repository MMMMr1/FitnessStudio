package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.recipe.RecipeDTO;
import it.academy.fitness_studio.core.dto.recipe.RecipeModel;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface IRecipeService {
    UUID create(RecipeDTO recipe) throws ValidationRecipeException;
    Pages<RecipeModel> getPageRecipe(Pageable paging);
    UUID update(UUID id, Instant version, RecipeDTO product) throws ValidationRecipeException;
}
