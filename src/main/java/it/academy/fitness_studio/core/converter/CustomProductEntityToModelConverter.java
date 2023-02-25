package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomProductEntityToModelConverter implements Converter<ProductEntity, ProductModel> {

    @Override
    public ProductModel convert(ProductEntity productEntity) {
        ProductModel productModel = new ProductModel(productEntity.getUuid(),
                productEntity.getDtCreate(),
                productEntity.getDtUpdate(),
                productEntity.getTitle(),
                productEntity.getWeight(),
                productEntity.getCalories(),
                productEntity.getProteins(),
                productEntity.getFats(),
                productEntity.getCarbohydrates());
        return productModel;
    }
}
