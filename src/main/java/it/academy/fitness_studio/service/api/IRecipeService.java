package it.academy.fitness_studio.service.api;



import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.RecipeDTO;

import java.time.Instant;
import java.util.UUID;

public interface IRecipeService {
    void create(RecipeDTO recipe);
//    void update(UUID id, Instant version, ProductDTO product);
//    Pages getPageProduct(int page, int size);
}
