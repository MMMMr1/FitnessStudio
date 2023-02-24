package it.academy.fitness_studio.service.api;



import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.entity.ProductEntity;

import java.time.Instant;
import java.util.UUID;

public interface IProductService {
    void create(ProductDTO product);
    void update(UUID id, Instant version, ProductDTO product);
    Pages getPageProduct(int page, int size);
    ProductEntity getProduct(UUID id);
}
