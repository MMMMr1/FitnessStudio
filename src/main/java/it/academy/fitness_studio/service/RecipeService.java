package it.academy.fitness_studio.service;


import it.academy.fitness_studio.core.converter.CustomProductDTOConverter;
import it.academy.fitness_studio.core.converter.CustomProductEntityConverter;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.IngredientDTO;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.dto.product.RecipeDTO;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.entity.IngredientEntity;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.entity.RecipeEntity;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class RecipeService implements IRecipeService {
//    @Autowired
    private final IRecipeDao dao;
    private final IProductService service;

    public RecipeService(IRecipeDao dao, IProductService service) {
        this.dao = dao;
        this.service = service;
    }

    @Override
    public void create(RecipeDTO recipe) {
        List<IngredientDTO> ingredientDTO = recipe.getComposition();

        List<IngredientEntity> collect = ingredientDTO.stream()
                .map(s -> new IngredientEntity(
                        service.getProduct(s.getProduct()), s.getWeight()))
                .collect(Collectors.toList());
        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(),
                Instant.now(),
                Instant.now(),
                recipe.getTitle(),
                collect);
        dao.save(recipeEntity);
    }
//
//    @Override
//    public void update(UUID id, Instant version, ProductDTO product) {
////        validate
//
//        ProductEntity productEntity = dao.findById(id).orElseThrow();
//        if ( version.toEpochMilli() == productEntity.getDtUpdate().toEpochMilli()){
//            productEntity.setCalories(product.getCalories());
//            productEntity.setCarbohydrates(product.getCarbohydrates());
//            productEntity.setFats(product.getFats());
//            productEntity.setProteins(product.getProteins());
//            productEntity.setTitle(product.getTitle());
//            productEntity.setWeight(product.getWeight());
//            dao.save(productEntity);
//        }
//    }
//    public Pages getPageProduct(int page, int size) {
//        Pageable paging = PageRequest.of(page, size);
//
//        Page<ProductEntity> all = dao.findAll(paging);
//
//        List<ProductModel> content = all.getContent().stream()
//                .map(s -> converterProductEntity.convert(s) )
//                .collect(Collectors.toList());
//        return  new Pages<ProductModel>(
//                all.getNumber(),
//                all.getSize(),
//                all.getTotalPages(),
//                all.getTotalElements(),
//                all.isFirst(),
//                all.getNumberOfElements(),
//                all.isLast(),
//                content);
//    }
}
