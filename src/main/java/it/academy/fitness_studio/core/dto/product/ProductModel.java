package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomBigDecimalConverter;
import it.academy.fitness_studio.core.converter.CustomInstantConverter;

import java.math.BigDecimal;
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
    @JsonSerialize(converter = CustomBigDecimalConverter.Serializer.class)
    @JsonProperty("proteins")
    private BigDecimal proteins;
    @JsonSerialize(converter = CustomBigDecimalConverter.Serializer.class)
    @JsonProperty("fats")
    private BigDecimal fats;
    @JsonSerialize(converter = CustomBigDecimalConverter.Serializer.class)
    @JsonProperty("carbohydrates")
    private BigDecimal carbohydrates;
    public ProductModel() {
    }
    public ProductModel(UUID uuid,
                        Instant dtCreate,
                        Instant dtUpdate,
                        String title,
                        Integer weight,
                        Integer calories,
                        BigDecimal proteins,
                        BigDecimal fats,
                        BigDecimal carbohydrates) {
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

    public BigDecimal getProteins() {
        return proteins;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }
    public static class ProductModelBuilder {
        private UUID uuid;
        private Instant dtCreate;
        private Instant dtUpdate;
        private String title;
        private  Integer weight;
        private  Integer calories;
        private BigDecimal proteins;
        private BigDecimal fats;
        private BigDecimal carbohydrates;
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

        public ProductModelBuilder setProteins(BigDecimal proteins) {
            this.proteins = proteins;
            return this;
        }

        public ProductModelBuilder setFats(BigDecimal fats) {
            this.fats = fats;
            return this;
        }

        public ProductModelBuilder setCarbohydrates(BigDecimal carbohydrates) {
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
