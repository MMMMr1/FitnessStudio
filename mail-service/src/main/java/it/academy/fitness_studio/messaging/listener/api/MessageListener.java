package it.academy.fitness_studio.messaging.listener.api;

public interface MessageListener <T> {
    void listenMessage (T message);
}
