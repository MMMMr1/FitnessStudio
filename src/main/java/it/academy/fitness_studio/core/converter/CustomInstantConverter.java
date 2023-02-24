package it.academy.fitness_studio.core.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;

public class CustomInstantConverter {
    public static class Deserializer extends StdConverter<Long, Instant> {
        @Override
        public Instant convert(Long value) {
            return value == null ? null : Instant.ofEpochMilli(value);
        }
    }

    public static class Serializer extends StdConverter<Instant, Long> {
        @Override
        public Long convert(Instant value) {
            return value == null ? null : value.toEpochMilli();
        }
    }
}
