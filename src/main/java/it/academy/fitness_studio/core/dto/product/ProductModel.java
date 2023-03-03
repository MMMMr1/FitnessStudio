package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomDoubleConverter;
import it.academy.fitness_studio.core.converter.CustomInstantConverter;

import java.time.Instant;
import java.util.UUID;

@JsonIgnoreProperties
public class ProductModel {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dtcreate")
    private Instant dtCreate;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dtupdate")
    private Instant dtUpdate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("calories")
    private Integer calories;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("proteins")
    private Double proteins;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("fats")
    private Double fats;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("carbohydrates")
    private Double carbohydrates;
    public ProductModel() {
    }
    public ProductModel(UUID uuid,
                        Instant dtCreate,
                        Instant dtUpdate,
                        String title,
                        Integer weight,
                        Integer calories,
                        Double proteins,
                        Double fats,
                        Double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
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
    public static class ProductModelBuilder {
        private UUID uuid;
        private Instant dtCreate;
        private Instant dtUpdate;
        private String title;
        private  Integer weight;
        private  Integer calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        private ProductModelBuilder() {
        }

        public static ProductModelBuilder create(){
            return new ProductModelBuilder();
        }

        public ProductModelBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ProductModelBuilder setDtCreate(Instant dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public ProductModelBuilder setDtUpdate(Instant dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public ProductModelBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
        public ProductModelBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public ProductModelBuilder setCalories(Integer calories) {
            this.calories = calories;
            return this;
        }

        public ProductModelBuilder setProteins(Double proteins) {
            this.proteins = proteins;
            return this;
        }

        public ProductModelBuilder setFats(Double fats) {
            this.fats = fats;
            return this;
        }

        public ProductModelBuilder setCarbohydrates(Double carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public ProductModel build(){
            return new ProductModel(
                    uuid,
                    dtCreate,
                    dtUpdate,
                    title,
                    weight,
                    calories,
                    proteins,
                    fats,
                    carbohydrates);
        }
    }
}
