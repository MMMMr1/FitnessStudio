package it.academy.fitness_studio.core.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.academy.fitness_studio.core.converter.CustomDoubleConverter;
import it.academy.fitness_studio.core.converter.CustomInstantConverter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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
    public static class RecipeModelBuilder {
        private UUID uuid;
        private Instant dtCreate;
        private Instant dtUpdate;
        private String title;
        private List<IngredientModel> composition;
        private  Integer weight;
        private  Integer calories;
        private Double proteins;
        private Double fats;
        private Double carbohydrates;
        private RecipeModelBuilder() {
        }
        public static RecipeModelBuilder create(){
            return new RecipeModelBuilder();
        }
        public RecipeModelBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }
        public RecipeModelBuilder setDtCreate(Instant dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }
        public RecipeModelBuilder setDtUpdate(Instant dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }
        public RecipeModelBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
        public RecipeModelBuilder setComposition(List<IngredientModel> composition) {
            this.composition = composition;
            return this;
        }
        public RecipeModelBuilder setWeight(List<IngredientModel> composition) {
            this.weight = composition.stream().mapToInt(IngredientModel::getWeight).sum();
            return this;
        }
        public RecipeModelBuilder setCalories( List<IngredientModel> composition) {
            this.calories = composition.stream().mapToInt(IngredientModel::getCalories).sum();
            return this;
        }
        public RecipeModelBuilder setProteins(List<IngredientModel> composition ) {
            this.proteins = composition.stream().mapToDouble(IngredientModel::getProteins).sum();
            return this;
        }
        public RecipeModelBuilder setFats( List<IngredientModel> composition) {
            this.fats = composition.stream().mapToDouble(IngredientModel::getFats).sum();
            return this;
        }
        public RecipeModelBuilder setCarbohydrates(List<IngredientModel> composition ) {
            this.carbohydrates = composition.stream().mapToDouble(IngredientModel::getCarbohydrates).sum();
            return this;
        }
        public RecipeModel build() {
            return new RecipeModel(
                    uuid,
                    dtCreate,
                    dtUpdate,
                    title,
                    composition,
                    weight,
                    calories,
                    proteins,
                    fats,
                    carbohydrates);
        }
    }
}
