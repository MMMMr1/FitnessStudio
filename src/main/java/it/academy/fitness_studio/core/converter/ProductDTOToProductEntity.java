package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductSavedDTO;
import it.academy.fitness_studio.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOToProductEntity implements Converter<ProductDTO, ProductEntity> {

    @Override
    public ProductEntity convert(ProductDTO product) {
        ProductSavedDTO productSavedDTO = ProductSavedDTO.ProductSavedDTOBuilder.create()
                .setTitle(product.getTitle())
                .setCalories(product.getCalories())
                .setCarbohydrates(product.getCarbohydrates())
                .setFats(product.getFats())
                .setProteins(product.getProteins())
                .setWeight(product.getWeight())
                .build();
        ProductEntity productEntity = new ProductEntity(productSavedDTO.getUuid(),
                productSavedDTO.getDtCreate(),
                productSavedDTO.getDtUpdate(),
                productSavedDTO.getTitle(),
                productSavedDTO.getWeight(),
                productSavedDTO.getCalories(),
                productSavedDTO.getProteins(),
                productSavedDTO.getFats(),
                productSavedDTO.getCarbohydrates());
        return productEntity;
    }
}
