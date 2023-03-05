package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.IngredientModel;
import it.academy.fitness_studio.core.dto.product.WeightAndTotal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class IngredientModelListToWeightAndTotal implements Converter<List<IngredientModel>, WeightAndTotal> {

    @Override
    public WeightAndTotal convert(List<IngredientModel> collect) {
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
        return new WeightAndTotal(weight,calories,proteins,fats,carbohydrates);
    }
}
