package it.academy.fitness_studio.core.dto.product;


import java.time.Instant;
import java.util.UUID;

public class ProductSavedDTO {
    private UUID uuid;

    private Instant dtCreate;

    private  Instant dtUpdate;
    private String title;
    private Integer weight;
    private Integer calories;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;

    public ProductSavedDTO(String title,
                           Integer weight,
                           Integer calories,
                           Double proteins,
                           Double fats,
                           Double carbohydrates) {
        uuid = UUID.randomUUID();
        dtCreate = Instant.now();
        dtUpdate = dtCreate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
    public ProductSavedDTO() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
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
