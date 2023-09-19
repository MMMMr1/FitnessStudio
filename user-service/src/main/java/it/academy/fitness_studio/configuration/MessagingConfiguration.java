package it.academy.fitness_studio.configuration;

import it.academy.fitness_studio.kafka.schema.Verification;
import it.academy.fitness_studio.messaging.VerificationMessageProducerImpl;
import it.academy.fitness_studio.messaging.api.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessagingConfiguration {
    @Bean
    public MessageProducer messageProducer(KafkaTemplate<String, Verification> verificationKafkaTemplate){
        return new VerificationMessageProducerImpl(verificationKafkaTemplate);
    }
}
