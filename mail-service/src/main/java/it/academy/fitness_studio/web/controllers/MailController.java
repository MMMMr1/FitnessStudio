package it.academy.fitness_studio.web.controllers;

import it.academy.fitness_studio.core.dto.VerificationMailDTO;
import it.academy.fitness_studio.kafka.schema.Verification;
import it.academy.fitness_studio.service.api.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService service;
    public MailController(MailService service) {
        this.service = service;
    }
    @RequestMapping( path = "/verification", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Validated Verification message) {
        service.sendVerificationMessage(message);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
