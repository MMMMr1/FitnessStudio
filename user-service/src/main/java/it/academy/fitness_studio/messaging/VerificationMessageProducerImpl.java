package it.academy.fitness_studio.messaging;

import it.academy.fitness_studio.messaging.api.MessageProducer;
import it.academy.fitness_studio.kafka.schema.Verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class VerificationMessageProducerImpl implements MessageProducer<Verification> {

    private KafkaTemplate<String, Verification> verificationKafkaTemplate;
    @Value("${avro.topic.name.verification}")
    private String verificationTopicName;
    private static final Logger logger =
            LoggerFactory.getLogger(VerificationMessageProducerImpl.class);
    public VerificationMessageProducerImpl(KafkaTemplate<String, Verification> verificationKafkaTemplate) {
        this.verificationKafkaTemplate = verificationKafkaTemplate;
    }

    @Override
    public void sendMessage(Verification message) {
         verificationKafkaTemplate.send(verificationTopicName,message);
        logger.info("Sent message to topic [" + verificationTopicName +
                        "] with offset=[" + message + "]");
//         ListenableFuture<SendResult<String, Verification>> future = (ListenableFuture<SendResult<String, Verification>>) verificationKafkaTemplate.send(
//                verificationTopicName, message
//        );
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Verification>>() {
//            @Override
//            public void onSuccess(SendResult<String, Verification> result) {
//                logger.info("Sent message to topic [" + verificationTopicName +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//                logger.error("Unable to send message to topic [" + verificationTopicName +
//                        "] due to : " + ex.getMessage());
//            }
//        });
    }
}
