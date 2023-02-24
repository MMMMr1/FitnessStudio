package it.academy.fitness_studio.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Entity
@Table(schema = "app",name = "recipes")
public class RecipeEntity {

    @Id
    private UUID uuid;
    private Instant dtCreate;
    private Instant dtUpdate;
    private String title;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinTable(schema = "app",
            name = "recipe_ingredient",
            joinColumns =
            @JoinColumn(name = "recipe_id"),
            inverseJoinColumns =
            @JoinColumn(name  = "ingredient_id")
    )
    private List<IngredientEntity> composition;

    public RecipeEntity() {
    }

    public RecipeEntity(UUID uuid,
                        Instant dtCreate,
                        Instant dtUpdate,
                        String title,
                        List<IngredientEntity> composition) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
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

    public List<IngredientEntity> getComposition() {
        return composition;
    }
    //    @Column(name = "weight")
//    private Integer weight;   /* Вес всего блюда считается на основе состава*/
//    @Column(name = "calories")
//    private Integer calories;
//    @Column(name = "proteins")
//    private Double proteins;
//    @Column(name = "fats")
//    private Double fats;
//    @Column(name = "carbohydrates")
//    private Double carbohydrates;
}
