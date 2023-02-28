package it.academy.fitness_studio.core.dto.product;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RecipeDTO {
    @NotBlank(message = "Title must not be blank")
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
