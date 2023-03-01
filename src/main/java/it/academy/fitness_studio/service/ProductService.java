package it.academy.fitness_studio.service;


import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.exception.ValidationProductException;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class ProductService implements IProductService {

    private final IProductDao dao;
    @Autowired
    private ConversionService conversionService;
    public ProductService(IProductDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(ProductDTO product) {
        validate(product);
        checkDoubleProduct(product);
//        boolean b = conversionService.canConvert(ProductDTO.class, ProductEntity.class);
        dao.save(conversionService.convert(product, ProductEntity.class));
    }

    @Override
    public void update(UUID id, Instant version, ProductDTO product) {
        validate(product);
        ProductEntity productEntity = dao.findById(id)
                .orElseThrow(() -> new ValidationProductException("There is no product with such id"));
        if ( version.toEpochMilli() == productEntity.getDtUpdate().toEpochMilli()){
            productEntity.setCalories(product.getCalories());
            productEntity.setCarbohydrates(product.getCarbohydrates());
            productEntity.setFats(product.getFats());
            productEntity.setProteins(product.getProteins());
            productEntity.setTitle(product.getTitle());
            productEntity.setWeight(product.getWeight());
            dao.save(productEntity);
        } else throw new ValidationProductException("Version is not correct");
    }
    public Pages getPageProduct(Pageable paging) {
//        Pageable paging = PageRequest.of(page, size);

        Page<ProductEntity> all = dao.findAll(paging);
        if (all == null){
            throw new RuntimeException("Data base is empty");
        }

        List<ProductModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s,ProductModel.class))
                .collect(Collectors.toList());
        return  new Pages<ProductModel>(
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
    public ProductModel getProduct(UUID id) {
        ProductEntity productEntity  = dao.findById(id)
                .orElseThrow(() -> new ValidationProductException("There is no product with such id"));
         return conversionService.convert(productEntity,ProductModel.class);
    }


    private void validate(ProductDTO product) throws ValidationProductException{
        String title = product.getTitle();

        if (title == null || title.isBlank()){
                throw new ValidationProductException("Title of product is not entered");
        }
        Integer calories = product.getCalories();
        if (calories <=0 || calories == null ){
            throw new ValidationProductException("Value of calories is incorrect");
        }
        Integer weight = product.getWeight();
        if (weight <=0 || weight == null){
            throw new ValidationProductException("Value of weight is incorrect");
        }
        Double carbohydrates = product.getCarbohydrates();
        if (carbohydrates <=0 || carbohydrates == null ){
            throw new ValidationProductException("Value of carbohydrates is incorrect");
        }
        Double fats = product.getFats();
        if ( fats <=0 || fats == null){
            throw new ValidationProductException("Value of fats is incorrect");
        }
    }
    private void checkDoubleProduct(ProductDTO product) throws ValidationProductException{
        String title = product.getTitle();
        if (dao.findByTitle(title) != null){
            throw new ValidationProductException("Product with such title has already exist");
        }
    }
}
