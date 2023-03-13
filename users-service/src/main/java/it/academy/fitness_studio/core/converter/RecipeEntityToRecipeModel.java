package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.ingredient.IngredientModel;
import it.academy.fitness_studio.core.dto.recipe.RecipeModel;
import it.academy.fitness_studio.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeEntityToRecipeModel implements Converter<RecipeEntity, RecipeModel> {

    private IngredientEntityToIngredientModel conversionService;

    public RecipeEntityToRecipeModel(IngredientEntityToIngredientModel conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public RecipeModel convert(RecipeEntity recipe) {
        List<IngredientModel> collect = recipe.getComposition().stream()
                .map(s -> conversionService.convert(s))
                .collect(Collectors.toList());
        Integer weight = collect.stream().mapToInt(IngredientModel::getWeight).sum();
        Integer calories = collect.stream().mapToInt(IngredientModel::getCalories).sum();
        BigDecimal proteins = collect.stream()
                .map(IngredientModel::getProteins)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal fats = collect.stream()
                .map(IngredientModel::getFats)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal carbohydrates = collect.stream()
                .map(IngredientModel::getCarbohydrates)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return RecipeModel.RecipeModelBuilder.create()
                .setUuid(recipe.getUuid()).setTitle(recipe.getTitle())
                .setDtCreate(recipe.getDtCreate()).setDtUpdate(recipe.getDtUpdate())
                .setComposition(collect).setCalories(calories)
                .setCarbohydrates(carbohydrates).setFats(fats)
                .setProteins(proteins).setWeight(weight).build();
    }
}
