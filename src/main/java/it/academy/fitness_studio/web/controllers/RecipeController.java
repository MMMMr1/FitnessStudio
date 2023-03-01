package it.academy.fitness_studio.web.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import it.academy.fitness_studio.core.dto.Pages;
import it.academy.fitness_studio.core.dto.product.ProductDTO;
import it.academy.fitness_studio.core.dto.product.ProductModel;
import it.academy.fitness_studio.core.dto.product.RecipeDTO;
import it.academy.fitness_studio.core.dto.product.RecipeModel;
import it.academy.fitness_studio.service.api.IProductService;
import it.academy.fitness_studio.service.api.IRecipeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated RecipeDTO product) {
        service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Pages<RecipeModel>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPageRecipe(paging));
    }
    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                        @PathVariable("dt_update") Long dtUpdate,
                                        @RequestBody @Validated RecipeDTO product) {
        Instant version = Instant.ofEpochMilli(dtUpdate);
        service.update(uuid, version, product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
