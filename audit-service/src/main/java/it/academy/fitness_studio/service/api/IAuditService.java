package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.audit.AuditDTO;
import it.academy.fitness_studio.core.dto.audit.AuditModel;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IAuditService
{
    void create(AuditDTO user);
    AuditModel getReport(UUID id);
    Pages<AuditModel> getAllReport(Pageable paging);
}
