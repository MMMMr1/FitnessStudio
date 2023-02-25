package it.academy.fitness_studio.service;


import it.academy.fitness_studio.core.converter.CustomProductEntityToModelConverter;
import it.academy.fitness_studio.core.converter.CustomProductModelToEntityConverter;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.*;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.entity.IngredientEntity;
import it.academy.fitness_studio.entity.RecipeEntity;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


public class RecipeService implements IRecipeService {
//    @Autowired
    private final IRecipeDao dao;
    private final IProductService service;
    private final CustomProductEntityToModelConverter converterProductEntity;
    private final CustomProductModelToEntityConverter modelToEntityConverter;

    public RecipeService(IRecipeDao dao, IProductService service,
                         CustomProductEntityToModelConverter converterProductEntity,
                         CustomProductModelToEntityConverter modelToEntityConverter
    ) {
        this.dao = dao;
        this.service = service;
        this.converterProductEntity = converterProductEntity;
        this.modelToEntityConverter = modelToEntityConverter;
    }

    @Override
    public void create(RecipeDTO recipeDTO) {
        RecipeSavedDTO recipe = new RecipeSavedDTO(recipeDTO);
        List<IngredientDTO> ingredientDTO = recipe.getComposition();
        List<IngredientEntity> collect = ingredientDTO.stream()
                .map(s -> new IngredientEntity(
                        modelToEntityConverter.convert(service.getProduct(s.getProduct())), s.getWeight()))
                .collect(Collectors.toList());
        RecipeEntity recipeEntity = new RecipeEntity(recipe.getUuid(),
                recipe.getDtCreate(),
                recipe.getDtUpdate(),
                recipe.getTitle(),
                collect);
        dao.save(recipeEntity);
    }

    @Override
    public Pages getPageRecipe(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<RecipeEntity> all = dao.findAll(paging);
        List<RecipeModel>  content  = new ArrayList<>();
        for (RecipeEntity recipe: all.getContent()) {
            List<IngredientModel> collect = recipe.getComposition().stream()
                    .map(s -> new IngredientModel(
                            service.getProduct(s.getProduct().getUuid()),
                             s.getWeight()))
                    .collect(Collectors.toList());
            Integer weight = collect.stream()
                    .mapToInt(IngredientModel::getWeight).sum();
            Integer calories = collect.stream()
                    .mapToInt(IngredientModel::getCalories).sum();
            Double proteins = collect.stream()
                    .mapToDouble(IngredientModel::getProteins).sum();
            Double fats = collect.stream()
                    .mapToDouble(IngredientModel::getFats).sum();
            Double carbohydrates = collect.stream()
                    .mapToDouble(IngredientModel::getCarbohydrates).sum();
            content.add(
                    new RecipeModel(recipe.getUuid(),
                    recipe.getDtCreate(),
                    recipe.getDtUpdate(),
                    recipe.getTitle(),
                    collect,
                    weight,
                    calories,
                    proteins,
                    fats,
                    carbohydrates
                    ));
        } return  new Pages<RecipeModel>(
                all.getNumber(),
                all.getSize(),
                all.getTotalPages(),
                all.getTotalElements(),
                all.isFirst(),
                all.getNumberOfElements(),
                all.isLast(),
                content);
    }

    @Override
    public void update(UUID id, Instant version, RecipeDTO product) {
//        validate
        RecipeEntity recipeEntity = dao.findById(id).orElseThrow();
        if (version.toEpochMilli() == recipeEntity.getDtUpdate().toEpochMilli()){
            recipeEntity.setTitle(product.getTitle());
            List<IngredientDTO> composition = product.getComposition();
            List<IngredientEntity> collect = composition.stream()
                    .map(s -> new IngredientEntity(
                            modelToEntityConverter.convert(service.getProduct(s.getProduct())), s.getWeight()))
                    .collect(Collectors.toList());
            recipeEntity.setComposition(collect);
            dao.save(recipeEntity);
        }
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

//    }
}
