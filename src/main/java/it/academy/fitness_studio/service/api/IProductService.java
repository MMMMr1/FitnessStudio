package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface IProductService {
    void create(ProductDTO product);
    void update(UUID id, Instant version, ProductDTO product);
    Pages getPageProduct(Pageable paging);
    ProductModel getProduct(UUID id);
}
