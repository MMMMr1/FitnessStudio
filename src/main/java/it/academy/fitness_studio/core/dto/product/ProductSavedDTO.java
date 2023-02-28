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
    public static class ProductSavedDTOBuilder{

        private String title;
        private Integer weight;
        private Integer calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        public static ProductSavedDTOBuilder create(){
            return new ProductSavedDTOBuilder();
        }



        public ProductSavedDTOBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductSavedDTOBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public ProductSavedDTOBuilder setCalories(Integer calories) {
            this.calories = calories;
            return this;
        }

        public ProductSavedDTOBuilder setProteins(Double proteins) {
            this.proteins = proteins;
            return this;
        }

        public ProductSavedDTOBuilder setFats(Double fats) {
            this.fats = fats;
            return this;
        }

        public ProductSavedDTOBuilder setCarbohydrates(Double carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }
        public ProductSavedDTO build(){
            return new ProductSavedDTO(
                    title,
                    weight,
                    calories,
                    proteins,
                    fats,
                    carbohydrates);
        }
    }
}

