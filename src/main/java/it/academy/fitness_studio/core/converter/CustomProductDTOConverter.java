package it.academy.fitness_studio.core.converter;

import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductSavedDTO;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserSavedDTO;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.entity.RoleEntity;
import it.academy.fitness_studio.entity.StatusEntity;
import it.academy.fitness_studio.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomProductDTOConverter implements Converter<ProductDTO, ProductEntity> {

    @Override
    public ProductEntity convert(ProductDTO product) {
        ProductSavedDTO productSavedDTO = new ProductSavedDTO(product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());
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
