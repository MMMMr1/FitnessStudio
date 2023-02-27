package it.academy.fitness_studio.core.dto.product;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductDTO {
    @NotBlank(message = "Title must not be blank")
    private String title;
    @Min(value = 1, message = "Weight should not be less than 1")
    private Integer weight;
    @Min(value = 1, message = "Calories should not be less than 1")
    private Integer calories;
    @Min(value = 0, message = "Proteins should not be less than 0.0")
    private Double proteins;
    @Min(value = 0, message = "Fats should not be less than 0.0")
    private Double fats;
    @Min(value = 0, message = "Carbohydrates should not be less than 0.0")
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
