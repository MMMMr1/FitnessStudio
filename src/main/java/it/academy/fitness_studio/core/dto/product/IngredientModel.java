package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomDoubleConverter;

import java.time.Instant;
import java.util.UUID;

public class IngredientModel {
    @JsonProperty("product")
    private ProductModel product;
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
    ;

    public IngredientModel() {
    }

    public IngredientModel(ProductModel product,
                           Integer weight,
                           Integer calories,
                           Double proteins,
                           Double fats,
                           Double carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    //    public IngredientModel(ProductModel product, Integer weight) {
//        this.product = product;
//        this.weight = weight;
//    }
    public ProductModel getProduct() {
        return product;
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

    public static class IngredientModelBuilder {
        private ProductModel product;
        private Integer weight;
        private Integer calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        ;

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

        public IngredientModelBuilder setCalories(ProductModel product) {
            checkWeight(product.getWeight());
            this.calories = weight * product.getCalories() / product.getWeight();
            return this;
        }

        public IngredientModelBuilder setProteins(ProductModel product) {
            checkWeight(product.getWeight());
            this.proteins = weight * product.getProteins() / product.getWeight();
            return this;
        }

        public IngredientModelBuilder setFats(ProductModel product) {
            checkWeight(product.getWeight());
            this.fats = weight * product.getFats() / product.getWeight();
            return this;
        }

        public IngredientModelBuilder setCarbohydrates(ProductModel product) {
            checkWeight(product.getWeight());
            this.carbohydrates = weight * product.getCarbohydrates() / product.getWeight();
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

        private void checkWeight(Integer weight) {
            if (weight <= 0) {
                throw new NumberFormatException("Check product with id : " +
                        product.getUuid() + ". Weight can not be <= 0");
            }
        }
    }


//    private Integer countInt(Integer val){
//        checkWeight(product.getWeight());
//       return   weight*val/product.getWeight();
//    }
//    private Double countDouble(Double val){
//        checkWeight(product.getWeight());
//        return weight*val/product.getWeight();
//    }

}
