
package it.academy.fitness_studio.dao.api;

import it.academy.fitness_studio.entity.RecipeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRecipeDao extends PagingAndSortingRepository<RecipeEntity, UUID>{
    RecipeEntity findByTitle(String title);
}
