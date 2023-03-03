package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductModel implements Converter<ProductEntity, ProductModel> {

    @Override
    public ProductModel convert(ProductEntity productEntity) {
        return ProductModel.ProductModelBuilder.create()
                .setUuid(productEntity.getUuid())
                .setDtCreate(productEntity.getDtCreate())
                .setDtUpdate(productEntity.getDtUpdate())
                .setCalories(productEntity.getCalories())
                .setFats(productEntity.getFats())
                .setCarbohydrates(productEntity.getCarbohydrates())
                .setProteins(productEntity.getProteins())
                .setWeight(productEntity.getWeight())
                .setTitle(productEntity.getTitle())
                .build();
    }
}
