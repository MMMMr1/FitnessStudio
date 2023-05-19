package it.academy.fitness_studio.service;

import it.academy.fitness_studio.core.dto.ingredient.IngredientDTO;
import it.academy.fitness_studio.core.dto.recipe.RecipeDTO;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;
import it.academy.fitness_studio.service.api.ProductService;
import it.academy.fitness_studio.service.api.ValidatorRecipe;

import java.util.List;
public class ValidatorRecipeImpl implements ValidatorRecipe {
    private final ProductService service;
    public ValidatorRecipeImpl(ProductService service) {
        this.service = service;
    }
    @Override
    public void validate(RecipeDTO recipe) throws ValidationRecipeException {
        ValidationRecipeException validationRecipeException = new ValidationRecipeException();
        String title = recipe.getTitle();
        if (title == null || title.isBlank()){
           validationRecipeException
                   .addSuppressed(new ValidationRecipeException("title",new Throwable( "Must not be blank")));
        }
        List<IngredientDTO> composition = recipe.getComposition();

        for (IngredientDTO ingredient:composition) {
            if (ingredient.getProduct() != null) {
                try {
                    service.getProduct(ingredient.getProduct());
                } catch (RuntimeException e) {
                    validationRecipeException
                            .addSuppressed(new ValidationRecipeException("product", new Throwable(ingredient.getProduct() + " does not exist")));
                }
            }else {
                validationRecipeException
                        .addSuppressed(new ValidationRecipeException("product", new Throwable("Must not be blank")));
            }
            if (ingredient.getWeight() <=0 || ingredient.getWeight() == null){
                validationRecipeException
                        .addSuppressed(new ValidationRecipeException("weight",new Throwable("Should be positive or zero")));
            }
        }
        if (validationRecipeException.getSuppressed().length>0){
            throw validationRecipeException;
        }
    }
}
