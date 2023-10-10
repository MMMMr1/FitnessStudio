package it.academy.fitness_studio.service.api;


import it.academy.fitness_studio.kafka.schema.Verification;

public interface MailService {
    void sendVerificationMessage(Verification message);
}

