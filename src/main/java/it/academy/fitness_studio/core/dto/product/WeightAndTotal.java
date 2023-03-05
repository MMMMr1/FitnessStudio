package it.academy.fitness_studio.core.dto.product;

import java.math.BigDecimal;

public class WeightAndTotal {
    private Integer weight;
    private Integer calories;
    private BigDecimal proteins;
    private BigDecimal fats;
    private BigDecimal carbohydrates;

    public WeightAndTotal(Integer weight, Integer calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }
}
