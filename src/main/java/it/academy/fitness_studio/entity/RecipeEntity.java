package it.academy.fitness_studio.entity;

import org.hibernate.annotations.CollectionId;

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
    @Version
    private Instant dtUpdate;
    private String title;
    @ElementCollection
    @CollectionTable(
            schema = "app",
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name ="recipe_id" )
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComposition(List<IngredientEntity> composition) {
        this.composition = composition;
    }
}
