package it.academy.fitness_studio.core.dto.recipe;

import it.academy.fitness_studio.core.dto.ingredient.IngredientDTO;

import java.util.List;

public class RecipeDTO {
    private String title;
    private List<IngredientDTO> composition;
    public RecipeDTO() {
    }
    public RecipeDTO(String title, List<IngredientDTO> composition) {
        this.title = title;
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }

    public List<IngredientDTO> getComposition() {
        return composition;
    }
}
