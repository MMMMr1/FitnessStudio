package it.academy.fitness_studio.core.dto.product;

import it.academy.fitness_studio.entity.ProductEntity;

import java.util.UUID;

public class IngredientDTO {
    private UUID product;
    private Integer weight;

    public IngredientDTO(UUID product, Integer weight) {
        this.product = product;
        this.weight = weight;
    }

    public IngredientDTO() {
    }

    public UUID getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }
}
