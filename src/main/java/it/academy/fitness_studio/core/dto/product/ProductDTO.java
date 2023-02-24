package it.academy.fitness_studio.core.dto.product;


import it.academy.fitness_studio.core.UserRole;
import it.academy.fitness_studio.core.UserStatus;

import javax.persistence.Id;

public class ProductDTO {
    private String title;
    private Integer weight;
    private Integer calories;
    private Double proteins;
    private Double fats;
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
