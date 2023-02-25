package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomDoubleConverter;
import it.academy.fitness_studio.core.converter.CustomInstantConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@JsonIgnoreProperties
public class RecipeModel {
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
    @JsonProperty("composition")
    private List<IngredientModel> composition;

    @JsonProperty("weight")
    private  Integer weight;
    @JsonProperty("calories")
    private  Integer calories;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("proteins")
    private Double proteins;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("fats")
    private Double fats;
    @JsonSerialize(converter = CustomDoubleConverter.Serializer.class)
    @JsonProperty("carbohydrates")
    private Double carbohydrates;

    public RecipeModel() {
    }

    public RecipeModel(UUID uuid,
                       Instant dtCreate,
                       Instant dtUpdate,
                       String title,
                       List<IngredientModel> composition,
                       Integer weight,
                       Integer calories,
                       Double proteins,
                       Double fats,
                       Double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
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

    public List<IngredientModel> getComposition() {
        return composition;
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
