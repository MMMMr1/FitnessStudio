package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOToProductEntity implements Converter<ProductDTO, ProductEntity> {
    @Override
    public ProductEntity convert(ProductDTO product) {
        return new ProductEntity(
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());
    }
}
