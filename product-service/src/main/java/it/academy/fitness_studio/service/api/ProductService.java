package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.UUID;

public interface ProductService {
    UUID create(ProductDTO product);
    UUID update(UUID id, Instant version, ProductDTO product);
    Pages getPageProduct(Pageable paging);
    ProductModel getProduct(UUID id);
}
