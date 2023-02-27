package it.academy.fitness_studio.dao.api;




import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductDao extends PagingAndSortingRepository<ProductEntity, UUID>
{

    ProductEntity findByTitle(String title);
}
