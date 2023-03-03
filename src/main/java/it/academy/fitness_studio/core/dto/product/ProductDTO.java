package it.academy.fitness_studio.core.dto.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ProductDTO {
    @NotBlank(message = "Must not be blank")
    private String title;
    @Positive (message = "Should be positive")
    private Integer weight;
    @PositiveOrZero(message = "Should be positive or zero")
    private Integer calories;
    @PositiveOrZero (message = "Should be positive or zero")
    private Double proteins;
    @PositiveOrZero (message = "Should be positive or zero")
    private Double fats;
    @PositiveOrZero (message = "Should be positive or zero")
    private Double carbohydrates;
    public ProductDTO(String title,
                      Integer weight,
                      Integer calories,
                      Double proteins,
                      Double fats,
                      Double carbohydrates) {
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
    public ProductDTO() {
    }
    public String getTitle() {
        return title;
    }
    public Integer getWeight() {
        return weight;
    }
    public Integer getCalories() {
        return calories;
    }
    public Double getProteins() {
        return proteins;
    }
    public Double getFats() {
        return fats;
    }
    public Double getCarbohydrates() {
        return carbohydrates;
    }
}
