package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomDoubleConverter;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;


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
    private Double carbohydrates;;

    public IngredientModel() {
    }

    public IngredientModel(ProductModel product, Integer weight) {
        this.product = product;
        this.weight = weight;
        this.calories = countInt(product.getCalories());
        this.proteins = countDouble(product.getProteins());
        this.fats = countDouble(product.getFats());
        this.carbohydrates = countDouble(product.getCarbohydrates());
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

    public Double getProteins() {
        return proteins;
    }

    public Double getFats() {
        return fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }
    private Integer countInt(Integer val){
        checkWeight(product.getWeight());
       return   weight*val/product.getWeight();
    }
    private Double countDouble(Double val){
        checkWeight(product.getWeight());
        return weight*val/product.getWeight();
    }
    private void checkWeight(Integer weight){
        if (weight <= 0) {
            throw new NumberFormatException("Check product with id : "+
                    product.getUuid() +". Weight can not be <= 0");
        }
    }

}
