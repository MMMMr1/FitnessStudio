package it.academy.fitness_studio.service;


import it.academy.fitness_studio.core.converter.CustomProductDTOConverter;
import it.academy.fitness_studio.core.converter.CustomProductEntityToModelConverter;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.service.api.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class ProductService implements IProductService {
//    @Autowired
    private final IProductDao dao;
    private final CustomProductDTOConverter converterProductDTO;
    private final CustomProductEntityToModelConverter converterProductEntity;

    public ProductService(IProductDao dao,
                          CustomProductDTOConverter converterProductDTO,
                          CustomProductEntityToModelConverter converterProductEntity) {
        this.dao = dao;
        this.converterProductDTO = converterProductDTO;
        this.converterProductEntity = converterProductEntity;
    }

    @Override
    public void create(ProductDTO product) {
//        Validate
//        check double post
        dao.save(converterProductDTO.convert(product));
    }

    @Override
    public void update(UUID id, Instant version, ProductDTO product) {
//        validate

        ProductEntity productEntity = dao.findById(id).orElseThrow();
        if ( version.toEpochMilli() == productEntity.getDtUpdate().toEpochMilli()){
            productEntity.setCalories(product.getCalories());
            productEntity.setCarbohydrates(product.getCarbohydrates());
            productEntity.setFats(product.getFats());
            productEntity.setProteins(product.getProteins());
            productEntity.setTitle(product.getTitle());
            productEntity.setWeight(product.getWeight());
            dao.save(productEntity);
        }
    }
    public Pages getPageProduct(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<ProductEntity> all = dao.findAll(paging);

        List<ProductModel> content = all.getContent().stream()
                .map(s -> converterProductEntity.convert(s) )
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
        ProductEntity productEntity = dao.findById(id).get();

        return converterProductEntity.convert(productEntity);

    }
}
