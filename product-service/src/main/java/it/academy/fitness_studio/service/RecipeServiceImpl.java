package it.academy.fitness_studio.service;


import it.academy.fitness_studio.audit.AuditCode;
import it.academy.fitness_studio.audit.AuditEntityType;
import it.academy.fitness_studio.audit.Auditable;
import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.ingredient.IngredientDTO;
import it.academy.fitness_studio.core.dto.product.*;
import it.academy.fitness_studio.core.dto.recipe.RecipeDTO;
import it.academy.fitness_studio.core.dto.recipe.RecipeModel;
import it.academy.fitness_studio.core.exception.InvalidVersionException;
import it.academy.fitness_studio.core.exception.RecipeAlreadyExistException;
import it.academy.fitness_studio.core.exception.RecipeNotFoundException;
import it.academy.fitness_studio.core.exception.ValidationRecipeException;
import it.academy.fitness_studio.dao.api.IRecipeDao;
import it.academy.fitness_studio.entity.IngredientEntity;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.entity.RecipeEntity;
import it.academy.fitness_studio.service.api.ProductService;
import it.academy.fitness_studio.service.api.RecipeService;
import it.academy.fitness_studio.service.api.ValidatorRecipe;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Transactional(readOnly = true)
public class RecipeServiceImpl implements RecipeService {
    private final IRecipeDao dao;
    private final ProductService service;
    private ConversionService conversionService;
    private final ValidatorRecipe validator;

    public RecipeServiceImpl(IRecipeDao dao,
                             ProductService service,
                             ConversionService conversionService,
                             ValidatorRecipe validator) {
        this.dao = dao;
        this.service = service;
        this.conversionService = conversionService;
        this.validator = validator;
    }
    @Override
    @Transactional
    @Auditable(auditCode = AuditCode.CREATED, auditType = AuditEntityType.RECIPE)
    public UUID create(@Validated RecipeDTO recipeDTO) throws ValidationRecipeException {
        validator.validate(recipeDTO);
        checkDoubleRecipe(recipeDTO);
        List<IngredientDTO> ingredientDTO = recipeDTO.getComposition();
        if (!conversionService.canConvert(ProductDTO.class, ProductEntity.class)) {
            throw new IllegalStateException("Can not convert IngredientDTO.class to IngredientEntity.class");
        }
        List<IngredientEntity> collect = ingredientDTO.stream()
                .map(s -> {
                    ProductModel product = service.getProduct(s.getProduct());
                    ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
                    return new IngredientEntity(productEntity, s.getWeight());
                })
                .collect(Collectors.toList());
        UUID uuid = UUID.randomUUID();
        Instant dt = Instant.now();
        dao.save(new RecipeEntity(uuid, dt, dt, recipeDTO.getTitle(), collect));
        return uuid;
    }

    @Override
    public Pages<RecipeModel> getPageRecipe(Pageable paging) {
        Page<RecipeEntity> all = dao.findAll(paging);
        if (!conversionService.canConvert(RecipeEntity.class, RecipeModel.class)) {
            throw new IllegalStateException("Can not convert RecipeEntity.class to RecipeModel.class");
        }
        List<RecipeModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s, RecipeModel.class))
                .collect(Collectors.toList());
        return Pages.PagesBuilder.<RecipeModel>create()
                .setNumber(all.getNumber()).setContent(content)
                .setFirst(all.isFirst()).setLast(all.isLast())
                .setNumberOfElements(all.getNumberOfElements())
                .setSize(all.getSize()).setTotalPages(all.getTotalPages())
                .setTotalElements(all.getTotalElements()).build();
    }
    @Override
    @Transactional
    @Auditable(auditCode = AuditCode.UPDATE, auditType = AuditEntityType.RECIPE)
    public UUID update(UUID id, Instant version, RecipeDTO product) throws ValidationRecipeException {
        validator.validate(product);
        RecipeEntity recipeEntity = dao.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("There is no recipe with such id"));
        if (version.toEpochMilli() != recipeEntity.getDtUpdate().toEpochMilli()) {
            throw new InvalidVersionException("Version is not correct");
        }
        if (!conversionService.canConvert(ProductModel.class, ProductEntity.class)) {
            throw new IllegalStateException("Can not convert ProductModel.class to ProductEntity.class");
        }
        List<IngredientEntity> collect = product.getComposition().stream()
                .map(s -> {
                    ProductModel productModel = service.getProduct(s.getProduct());
                    ProductEntity productEntity = conversionService.convert(productModel, ProductEntity.class);
                    return new IngredientEntity(productEntity, s.getWeight());
                })
                .collect(Collectors.toList());
        recipeEntity.setTitle(product.getTitle());
        recipeEntity.setComposition(collect);
        dao.save(recipeEntity);
        return id;
    }

    private void checkDoubleRecipe(RecipeDTO recipe) {
        String title = recipe.getTitle();
        if (dao.findByTitle(title) != null) {
            throw new RecipeAlreadyExistException("Product with title '" + title + "' has already existed");
        }
    }
}


