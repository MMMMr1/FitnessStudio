package it.academy.fitness_studio.messaging.listener;

import it.academy.fitness_studio.kafka.schema.Verification;
import it.academy.fitness_studio.messaging.listener.api.MessageListener;
import it.academy.fitness_studio.service.api.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${kafka.topic.verification}", groupId = "${spring.kafka.consumer.group-id}")
public class VerificationMessageListenerImpl implements MessageListener <Verification> {
    private final MailService mailService;
    private static final Logger logger =
            LoggerFactory.getLogger(VerificationMessageListenerImpl.class);
    public VerificationMessageListenerImpl(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaHandler
    public void listenMessage(Verification message) {
        logger.info("--------> mail-service "+ message.getMail().toString());
        mailService.sendVerificationMessage(message);
    }
}
