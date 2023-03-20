package it.academy.fitness_studio.core.dto.ingredient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.jackson.CustomBigDecimalConverter;
import it.academy.fitness_studio.core.dto.product.ProductModel;

import java.math.BigDecimal;


public class IngredientModel {
    @JsonProperty("product")
    private ProductModel product;
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
    public IngredientModel() {
    }

    public IngredientModel(ProductModel product,
                           Integer weight,
                           Integer calories,
                           BigDecimal proteins,
                           BigDecimal fats,
                           BigDecimal carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
    public ProductModel getProduct() {
        return product;
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

    public static class IngredientModelBuilder {
        private ProductModel product;
        private Integer weight;
        private Integer calories;
        private BigDecimal proteins;
        private BigDecimal fats;
        private BigDecimal carbohydrates;
        private IngredientModelBuilder() {
        }
        public static IngredientModelBuilder create() {
            return new IngredientModelBuilder();
        }
        public IngredientModelBuilder setProduct(ProductModel product) {
            this.product = product;
            return this;
        }
        public IngredientModelBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }
        public IngredientModelBuilder setCalories(Integer calories) {

            this.calories = calories;
            return this;
        }
        public IngredientModelBuilder setProteins(BigDecimal proteins) {
            this.proteins = proteins;
            return this;
        }
        public IngredientModelBuilder setFats(BigDecimal fats) {
            this.fats = fats;
            return this;
        }
        public IngredientModelBuilder setCarbohydrates(BigDecimal carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }
        public IngredientModel build() {
            return new IngredientModel(
                    product,
                    weight,
                    calories,
                    proteins,
                    fats,
                    carbohydrates);
        }
    }
}
