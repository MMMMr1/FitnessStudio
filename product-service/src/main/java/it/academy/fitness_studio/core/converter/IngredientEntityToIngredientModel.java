package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.ingredient.IngredientModel;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.entity.IngredientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class IngredientEntityToIngredientModel implements Converter<IngredientEntity, IngredientModel> {

    private ProductEntityToProductModel conversionService;

    public IngredientEntityToIngredientModel(ProductEntityToProductModel conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public IngredientModel convert(IngredientEntity ingredient) {
        ProductModel product = conversionService.convert(ingredient.getProduct());
        BigDecimal factor = (BigDecimal.valueOf(ingredient.getWeight())).divide(BigDecimal.valueOf(product.getWeight()), RoundingMode.HALF_DOWN);
        Integer calories = factor.multiply(BigDecimal.valueOf(product.getCalories())).intValue();
        BigDecimal proteins = factor.multiply(product.getProteins());
        BigDecimal fats = factor.multiply(product.getFats());
        BigDecimal carbohydrates = factor.multiply(product.getCarbohydrates());
        return IngredientModel.IngredientModelBuilder.create()
                .setProduct(product)
                .setWeight(ingredient.getWeight())
                .setCalories(calories)
                .setCarbohydrates(carbohydrates)
                .setFats(fats).setProteins(proteins)
                .build();
    }
}
