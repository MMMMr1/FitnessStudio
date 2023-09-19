package it.academy.fitness_studio.messaging.api;

public interface MessageProducer<T> {
    void sendMessage( T message);
}
