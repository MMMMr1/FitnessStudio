package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.kafka.schema.Verification;
import it.academy.fitness_studio.messaging.listener.VerificationMessageListenerImpl;
import it.academy.fitness_studio.messaging.listener.api.MessageListener;
import it.academy.fitness_studio.service.api.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {
    @Bean
    public MessageListener <Verification> messageListener(MailService emailService){
        return new VerificationMessageListenerImpl(emailService);

    }
}
