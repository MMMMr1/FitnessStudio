package it.academy.fitness_studio.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "app",name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "dtcreate")
    private Instant dtCreate;
    @Version
    @Column(name = "dtupdate")
    private Instant dtUpdate;
    @Column(name = "title")
    private String title;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "calories")
    private Integer calories;
    @Column(name = "proteins")
    private Double proteins;
    @Column(name = "fats")
    private Double fats;
    @Column(name = "carbohydrates")
    private Double carbohydrates;
    public ProductEntity() {
    }
    public ProductEntity(UUID uuid,
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
    public void setTitle(String title) {
        this.title = title;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }
    public void setFats(Double fats) {
        this.fats = fats;
    }
    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
