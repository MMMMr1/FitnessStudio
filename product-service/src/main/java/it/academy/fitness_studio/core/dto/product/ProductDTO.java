package it.academy.fitness_studio.core.dto.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductDTO {
    @NotBlank(message = "Must not be blank")
    private String title;
    @Positive (message = "Should be positive")
    private Integer weight;
    @PositiveOrZero(message = "Should be positive or zero")
    private Integer calories;
    @PositiveOrZero (message = "Should be positive or zero")
    private BigDecimal proteins;
    @PositiveOrZero (message = "Should be positive or zero")
    private BigDecimal fats;
    @PositiveOrZero (message = "Should be positive or zero")
    private BigDecimal carbohydrates;
    public ProductDTO(String title,
                      Integer weight,
                      Integer calories,
                      BigDecimal proteins,
                      BigDecimal fats,
                      BigDecimal carbohydrates) {
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
