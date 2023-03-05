package it.academy.fitness_studio.service;


import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.*;
import it.academy.fitness_studio.core.exception.InvalidVersionException;
import it.academy.fitness_studio.core.exception.RecipeAlreadyExistException;
import it.academy.fitness_studio.core.exception.RecipeNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.entity.IngredientEntity;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.entity.RecipeEntity;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import it.academy.fitness_studio.service.api.IValidatorRecipe;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class RecipeService implements IRecipeService {
    private final IRecipeDao dao;
    private final IProductService service;
    private ConversionService conversionService;
    private final IValidatorRecipe validator;

    public RecipeService(IRecipeDao dao,
                         IProductService service,
                         ConversionService conversionService,
                         IValidatorRecipe validator) {
        this.dao = dao;
        this.service = service;
        this.conversionService = conversionService;
        this.validator = validator;
    }

    @Override
    public void create(@Validated RecipeDTO recipeDTO) throws ValidationRecipeException {
        validator.validate(recipeDTO);
        checkDoubleRecipe(recipeDTO);
        List<IngredientDTO> ingredientDTO = recipeDTO.getComposition();
        if (!conversionService.canConvert(ProductDTO.class, ProductEntity.class)) {
            throw new RuntimeException("Can not convert IngredientDTO.class to IngredientEntity.class");
        }
        List<IngredientEntity> collect = ingredientDTO.stream()
                .map(s -> new IngredientEntity(
                        conversionService.convert(service.getProduct(s.getProduct()), ProductEntity.class),
                        s.getWeight()))
                .collect(Collectors.toList());
        UUID uuid = UUID.randomUUID();
        Instant dt = Instant.now();
        dao.save(new RecipeEntity(uuid, dt, dt, recipeDTO.getTitle(), collect));
    }

    @Override
    public Pages<RecipeModel> getPageRecipe(Pageable paging) {
        Page<RecipeEntity> all = dao.findAll(paging);
        List<RecipeModel> content = convertListOfRecipeEntityToRecipeModel(all);
        return Pages.PagesBuilder.<RecipeModel>create()
                .setNumber(all.getNumber()).setContent(content)
                .setFirst(all.isFirst()).setLast(all.isLast())
                .setNumberOfElements(all.getNumberOfElements())
                .setSize(all.getSize()).setTotalPages(all.getTotalPages())
                .setTotalElements(all.getTotalElements()).build();
    }

    @Override
    public void update(UUID id, Instant version, RecipeDTO product) throws ValidationRecipeException {
        validator.validate(product);
        RecipeEntity recipeEntity = dao.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("There is no recipe with such id"));
        if (version.toEpochMilli() != recipeEntity.getDtUpdate().toEpochMilli()) {
            throw new InvalidVersionException("Version is not correct");
        }
        if (!conversionService.canConvert(IngredientDTO.class, IngredientEntity.class)) {
            throw new RuntimeException("Can not convert IngredientDTO.class to IngredientEntity.class");
        }
        List<IngredientEntity> collect = product.getComposition().stream()
                .map(s -> new IngredientEntity(
                        conversionService.convert(service.getProduct(s.getProduct()), ProductEntity.class),
                        s.getWeight()))
                .collect(Collectors.toList());
        recipeEntity.setTitle(product.getTitle());
        recipeEntity.setComposition(collect);
        dao.save(recipeEntity);
    }

    private List<RecipeModel> convertListOfRecipeEntityToRecipeModel(Page<RecipeEntity> all) {
        List<RecipeModel> content = new ArrayList<>();
        for (RecipeEntity recipe : all.getContent()) {

            List<IngredientModel> collect = recipe.getComposition().stream()
                    .map(s -> {
                        ProductModel product = service.getProduct(s.getProduct().getUuid());
                        WeightAndTotal weightAndTotal = counter(product, s.getWeight());
                        return IngredientModel.IngredientModelBuilder.create()
                                .setProduct(product).setWeightAndTotal(weightAndTotal)
//                                .setWeight(s.getWeight())
//                                .setCalories(weightAndTotal.getCalories()).setCarbohydrates(weightAndTotal.getCarbohydrates())
//                                .setFats(weightAndTotal.getFats()).setProteins(weightAndTotal.getProteins())
                                .build();})
                    .collect(Collectors.toList());
            WeightAndTotal recipeWeightAndTotal = counter(collect);
            RecipeModel.RecipeModelBuilder recipeModel = RecipeModel.RecipeModelBuilder.create()
                    .setUuid(recipe.getUuid()).setTitle(recipe.getTitle())
                    .setDtCreate(recipe.getDtCreate()).setDtUpdate(recipe.getDtUpdate())
                    .setComposition(collect).setCalories(recipeWeightAndTotal.getCalories())
                    .setCarbohydrates(recipeWeightAndTotal.getCarbohydrates()).setFats(recipeWeightAndTotal.getFats())
                    .setProteins(recipeWeightAndTotal.getProteins()).setWeight(recipeWeightAndTotal.getWeight());

            content.add(recipeModel.build());
        }
        return content;
    }
    private WeightAndTotal counter(ProductModel product, Integer weight){
        BigDecimal factor = (BigDecimal.valueOf(weight)).divide(BigDecimal.valueOf(product.getWeight()));
        Integer calories = factor.multiply(BigDecimal.valueOf(product.getCalories())).intValue();
        BigDecimal proteins = factor.multiply(product.getProteins()) ;
        BigDecimal fats =factor.multiply(product.getFats()) ;
        BigDecimal carbohydrates = factor.multiply(product.getCarbohydrates()) ;
        return new WeightAndTotal(weight,calories,proteins,fats,carbohydrates);
    }
    private WeightAndTotal counter(List<IngredientModel> collect) {
        Integer weight = collect.stream().mapToInt(IngredientModel::getWeight).sum();
        Integer calories = collect.stream().mapToInt(IngredientModel::getCalories).sum();
        BigDecimal proteins = collect.stream()
                .map(IngredientModel::getProteins)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal fats = collect.stream()
                .map(IngredientModel::getFats)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal carbohydrates = collect.stream()
                .map(IngredientModel::getCarbohydrates)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new WeightAndTotal(weight,calories,proteins,fats,carbohydrates);
    }

    private void checkDoubleRecipe(RecipeDTO recipe) {
        String title = recipe.getTitle();
        if (dao.findByTitle(title) != null) {
            throw new RecipeAlreadyExistException("Product with title '" + title + "' has already existed");
        }
    }
//    private List<RecipeModel> createListOfRecipeModel(Page<RecipeEntity> all){
//        List<RecipeModel>  content  = new ArrayList<>();
//        for (RecipeEntity recipe: all.getContent()) {
//            List<IngredientModel> collect = recipe.getComposition().stream()
//                    .map(s -> {
//                        ProductModel product = service.getProduct(s.getProduct().getUuid());
//                        return IngredientModel.IngredientModelBuilder.create()
//                                .setProduct(product).setWeight(s.getWeight()).setCalories(product)
//                                .setCarbohydrates(product).setFats(product).setProteins(product)
//                                .build();
//                    })
//                    .collect(Collectors.toList());
//
//            Integer weight = collect.stream().mapToInt(IngredientModel::getWeight).sum();
//            Integer calories = collect.stream().mapToInt(IngredientModel::getCalories).sum();
//            Double proteins = collect.stream().mapToDouble(IngredientModel::getProteins).sum();
//            Double fats = collect.stream().mapToDouble(IngredientModel::getFats).sum();
//            Double carbohydrates = collect.stream().mapToDouble(IngredientModel::getCarbohydrates).sum();
//
//            RecipeModel.RecipeModelBuilder recipeModel = RecipeModel.RecipeModelBuilder.create()
//                    .setUuid(recipe.getUuid()).setTitle(recipe.getTitle())
//                    .setDtCreate(recipe.getDtCreate()).setDtUpdate(recipe.getDtUpdate())
//                    .setCalories(calories).setCarbohydrates(carbohydrates)
//                    .setFats(fats).setProteins(proteins)
//                    .setWeight(weight).setComposition(collect);
//            content.add(recipeModel.build());
//        }return content;
//    }

}
