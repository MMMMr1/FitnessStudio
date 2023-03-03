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

import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    public void create( @Validated RecipeDTO recipeDTO) throws ValidationRecipeException {
        validator.validate(recipeDTO);
        checkDoubleRecipe(recipeDTO);
        List<IngredientDTO> ingredientDTO = recipeDTO.getComposition();
        if (!conversionService.canConvert(IngredientDTO.class, IngredientEntity.class)) {
            throw new RuntimeException("Can not convert IngredientDTO.class to IngredientEntity.class");
        }
        List<IngredientEntity> collect = ingredientDTO.stream()
                .map(s ->  new IngredientEntity(
                        conversionService.convert(service.getProduct(s.getProduct()), ProductEntity.class),
                        s.getWeight()))
                .collect(Collectors.toList());
        UUID uuid = UUID.randomUUID();
        Instant dt = Instant.now();
        dao.save(new RecipeEntity(uuid, dt, dt, recipeDTO.getTitle(), collect));
    }

    @Override
    public Pages getPageRecipe(Pageable paging) {
        Page<RecipeEntity> all = dao.findAll(paging);
        List<RecipeModel>  content  = new ArrayList<>();
        for (RecipeEntity recipe: all.getContent()) {
            List<IngredientModel> collect = recipe.getComposition().stream()
                    .map(s -> {
                        ProductModel product = service.getProduct(s.getProduct().getUuid());
                        return IngredientModel.IngredientModelBuilder.create()
                                .setProduct(product).setWeight(s.getWeight()).setCalories(product)
                                .setCarbohydrates(product).setFats(product).setProteins(product)
                                .build();
                    })
                    .collect(Collectors.toList());
            Stream<IngredientModel> stream = collect.stream();
            Integer weight = stream.mapToInt(IngredientModel::getWeight).sum();
            Integer calories = stream.mapToInt(IngredientModel::getCalories).sum();
            Double proteins = stream.mapToDouble(IngredientModel::getProteins).sum();
            Double fats = stream.mapToDouble(IngredientModel::getFats).sum();
            Double carbohydrates = stream.mapToDouble(IngredientModel::getCarbohydrates).sum();

            RecipeModel.RecipeModelBuilder builder = RecipeModel.RecipeModelBuilder.create()
                    .setCalories(calories) .setCarbohydrates(carbohydrates)
                    .setDtCreate(recipe.getDtCreate()).setDtUpdate(recipe.getDtUpdate())
                    .setFats(fats).setProteins(proteins)
                    .setUuid(recipe.getUuid()).setWeight(weight)
                    .setComposition(collect).setTitle(recipe.getTitle());
            content.add(builder.build());
        } return Pages.PagesBuilder.create().setNumber(all.getNumber())
                    .setContent(content).setFirst(all.isFirst())
                    .setLast(all.isLast()).setNumberOfElements(all.getNumberOfElements())
                    .setSize(all.getSize()).setTotalPages(all.getTotalPages())
                    .setTotalElements(all.getTotalElements()).build();
    }
    @Override
    public void update(UUID id, Instant version, RecipeDTO product) throws ValidationRecipeException{
        validator.validate(product);
        RecipeEntity recipeEntity = dao.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("There is no recipe with such id"));
        if (version.toEpochMilli() == recipeEntity.getDtUpdate().toEpochMilli()){
            recipeEntity.setTitle(product.getTitle());
            List<IngredientDTO> composition = product.getComposition();
            if (!conversionService.canConvert(IngredientDTO.class, IngredientEntity.class)) {
                throw new RuntimeException("Can not convert IngredientDTO.class to IngredientEntity.class");
            }
            List<IngredientEntity> collect = composition.stream()
                    .map(s -> new IngredientEntity(
                            conversionService.convert(service.getProduct(s.getProduct()),ProductEntity.class),
                            s.getWeight()))
                    .collect(Collectors.toList());
            recipeEntity.setComposition(collect);
            dao.save(recipeEntity);
        }  else throw new InvalidVersionException("Version is not correct");
    }
    private void checkDoubleRecipe(RecipeDTO recipe) {
        String title = recipe.getTitle();
        if (dao.findByTitle(title) != null){
            throw new RecipeAlreadyExistException("Product with title '"+title+"' has already existed");
        }
    }
}
