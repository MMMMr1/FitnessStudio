package it.academy.fitness_studio.core.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class CustomDoubleConverter {
    public static class Serializer extends StdConverter<Double, BigDecimal> {
        @Override
        public BigDecimal convert(Double value) {
            return value == null ? null : BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        }
    }
}
