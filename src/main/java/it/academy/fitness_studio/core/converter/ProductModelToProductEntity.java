package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductModelToProductEntity implements Converter<ProductModel, ProductEntity> {
    @Override
    public ProductEntity convert(ProductModel product) {
        return new ProductEntity(product.getUuid(),
                product.getDtCreate(),
                product.getDtUpdate(),
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());
    }
}
