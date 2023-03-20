package it.academy.fitness_studio.service.api;

import it.academy.fitness_studio.core.dto.VerificationMailDTO;

public interface IMailService {
    void sendVerificationMessage(VerificationMailDTO mailDTO);
}

