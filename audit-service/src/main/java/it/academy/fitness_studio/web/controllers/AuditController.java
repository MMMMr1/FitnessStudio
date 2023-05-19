package it.academy.fitness_studio.web.controllers;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.audit.AuditDTO;
import it.academy.fitness_studio.core.dto.audit.AuditModel;
import it.academy.fitness_studio.service.api.AuditService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private AuditService service;
    public AuditController( AuditService service) {
        this.service = service;
    }
    @RequestMapping(path = "/report",method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated AuditDTO user)   {
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Pages<AuditModel>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllReport(paging));
    }
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<AuditModel> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getReport(uuid));
    }
}
