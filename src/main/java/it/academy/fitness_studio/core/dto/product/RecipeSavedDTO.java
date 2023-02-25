package it.academy.fitness_studio.core.dto.product;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class RecipeSavedDTO {
    private UUID uuid;

    private Instant dtCreate;

    private  Instant dtUpdate;
    private RecipeDTO recipeDTO;


    public RecipeSavedDTO(RecipeDTO recipeDTO) {
        uuid = UUID.randomUUID();
        dtCreate = Instant.now();
        dtUpdate = dtCreate;
        this.recipeDTO = recipeDTO;

    }
    public RecipeSavedDTO() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }
    public String getTitle() {
        return recipeDTO.getTitle();
    }

    public List<IngredientDTO> getComposition() {
        return recipeDTO.getComposition();
    }
}
