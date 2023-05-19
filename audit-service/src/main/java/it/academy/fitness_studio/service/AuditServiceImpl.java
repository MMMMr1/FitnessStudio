package it.academy.fitness_studio.service;

import it.academy.fitness_studio.audit.AuditCode;
import it.academy.fitness_studio.audit.AuditEntityType;
import it.academy.fitness_studio.audit.Auditable;
import it.academy.fitness_studio.audit.Audited;
import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.audit.AuditDTO;
import it.academy.fitness_studio.core.dto.audit.AuditModel;
import it.academy.fitness_studio.core.exception.AuditNotFoundException;
import it.academy.fitness_studio.dao.api.IAuditDao;
import it.academy.fitness_studio.entity.AuditEntity;
import it.academy.fitness_studio.service.api.AuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AuditServiceImpl implements AuditService {
    private final IAuditDao dao;
    private ConversionService conversionService;
    public AuditServiceImpl(IAuditDao dao,
                            ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }
    @Override
    public void create(@Validated AuditDTO user) {
        if (!conversionService.canConvert(AuditDTO.class, AuditEntity.class)) {
            throw new RuntimeException("Can not convert AuditDTO.class to AuditEntity.class");
        }
        AuditEntity userEntity = conversionService.convert(user, AuditEntity.class);
        userEntity.setId(UUID.randomUUID());
        Instant dtCreated = Instant.now();
        userEntity.setDtCreate(dtCreated);
        dao.save(userEntity);
    }
    @Override
    @Audited(auditCode = AuditCode.GETAll,auditType = AuditEntityType.REPORT)
    public Pages<AuditModel> getAllReport(Pageable paging) {
        Page<AuditEntity> all = dao.findAll(paging);
        if (!conversionService.canConvert(AuditEntity.class, AuditModel.class)) {
            throw new IllegalStateException("Can not convert AuditEntity.class to AuditModel.class");
        }
        List<AuditModel> content = all.getContent().stream()
                .map(s -> conversionService.convert(s, AuditModel.class))
                .collect(Collectors.toList());
        return Pages.PagesBuilder.<AuditModel>create()
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
    @Auditable(auditCode = AuditCode.GET, auditType = AuditEntityType.REPORT)
    public AuditModel getReport(UUID id) {
        AuditEntity userEntity = dao.findById(id)
                .orElseThrow(() -> new AuditNotFoundException("There is no report with such id"));
        if (!conversionService.canConvert(AuditEntity.class, AuditModel.class)) {
            throw new IllegalStateException("Can not convert AuditEntity.class to AuditModel.class");
        }
        return conversionService.convert(userEntity, AuditModel.class);
    }
}
