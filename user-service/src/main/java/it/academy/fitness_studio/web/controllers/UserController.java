package it.academy.fitness_studio.web.controllers;

import it.academy.fitness_studio.core.dto.pages.Pages;
import it.academy.fitness_studio.core.dto.user.UserDTO;
import it.academy.fitness_studio.core.dto.user.UserModel;
import it.academy.fitness_studio.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService service;
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);
    public UserController(UserService service) {
        this.service = service;
    }
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated UserDTO user)   {
        logger.info("create "+ user);
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Pages<UserModel>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPageUser(paging));
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> get(@PathVariable("uuid") UUID uuid) {
        logger.info("get user with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getUser(uuid));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                        @PathVariable("dt_update") Instant dtUpdate,
                                        @RequestBody @Validated UserDTO user) {
        service.update(uuid, dtUpdate, user);
        logger.info("update user with "+ uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
