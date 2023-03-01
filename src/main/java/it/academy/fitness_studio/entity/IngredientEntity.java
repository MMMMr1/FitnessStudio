package it.academy.fitness_studio.entity;


import javax.persistence.*;
@Embeddable
public class IngredientEntity {
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false, updatable = false
    )
    private ProductEntity product;
    private Integer weight;
    public IngredientEntity(ProductEntity product, Integer weight) {
        this.product = product;
        this.weight = weight;
    }
    public IngredientEntity() {
    }
    public ProductEntity getProduct() {
        return product;
    }
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    public Integer getWeight() {
        return weight;
    }
}
