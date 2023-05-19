package it.academy.fitness_studio.service;

import it.academy.fitness_studio.audit.AuditCode;
import it.academy.fitness_studio.audit.AuditEntityType;
import it.academy.fitness_studio.audit.Auditable;
import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.exception.InvalidVersionException;
import it.academy.fitness_studio.core.exception.ProductNotFoundException;
import it.academy.fitness_studio.dao.api.IProductDao;
import it.academy.fitness_studio.entity.ProductEntity;
import it.academy.fitness_studio.service.api.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final IProductDao dao;
    private ConversionService conversionService;
    public ProductServiceImpl(IProductDao dao,
                              ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }
    @Override
    @Transactional
    @Auditable(auditCode = AuditCode.CREATED, auditType = AuditEntityType.PRODUCT)
    public UUID create( @Validated ProductDTO product) {
         if (!conversionService.canConvert(ProductDTO.class, ProductEntity.class)) {
             throw new IllegalStateException("Can not convert ProductDTO.class");
         }
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        UUID uuid = UUID.randomUUID();
        productEntity.setUuid(uuid);
        Instant dtCreated =  Instant.now();
        Instant dtUpdated = dtCreated;
        productEntity.setDtCreate(dtCreated);
        productEntity.setDtUpdate(dtUpdated);
        dao.save(productEntity);
        return uuid;
    }
    @Override
    @Transactional
    @Auditable(auditCode = AuditCode.UPDATE, auditType = AuditEntityType.PRODUCT)
    public UUID update(UUID id, Instant version,@Validated ProductDTO product) {
        ProductEntity productEntity = dao.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("There is no product with such id"));
        if(version.toEpochMilli() != productEntity.getDtUpdate().toEpochMilli()){
            throw new InvalidVersionException("Version is not correct");
        }
            productEntity.setCalories(product.getCalories());
            productEntity.setCarbohydrates(product.getCarbohydrates());
            productEntity.setFats(product.getFats());
            productEntity.setProteins(product.getProteins());
            productEntity.setTitle(product.getTitle());
            productEntity.setWeight(product.getWeight());
            dao.save(productEntity);
            return id;
    }
    public Pages<ProductModel> getPageProduct(Pageable paging) {
        Page<ProductEntity> all = dao.findAll(paging);
        if (all == null){
            throw new RuntimeException("Data base is empty");
        }
        if (!conversionService.canConvert(ProductEntity.class, ProductModel.class)) {
            throw new IllegalStateException("Can not convert ProductEntity.class to ProductModel.class");
        }
        List<ProductModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s,ProductModel.class))
                .collect(Collectors.toList());

        return Pages.PagesBuilder.<ProductModel>create( )
                .setNumber(all.getNumber())
                .setContent(content)
                .setFirst(all.isFirst())
                .setLast(all.isLast())
                .setNumberOfElements(all.getNumberOfElements())
                .setSize(all.getSize())
                .setTotalPages(all.getTotalPages())
                .setTotalElements(all.getTotalElements())
                .build();
    }
    @Override
    public ProductModel getProduct(UUID id) {
        ProductEntity productEntity  = dao.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("There is no product with id '"+id+"'"));
        if (!conversionService.canConvert(ProductEntity.class, ProductModel.class)) {
            throw new IllegalStateException("Can not convert ProductDTO.class to ProductModel.class");
        }
         return conversionService.convert(productEntity,ProductModel.class);
    }
}
