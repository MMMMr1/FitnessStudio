package it.academy.fitness_studio.core.converter.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomBigDecimalConverter {
    public static class Serializer extends StdConverter<BigDecimal, BigDecimal> {
        @Override
        public BigDecimal convert(BigDecimal bigDecimal) {
            return bigDecimal == null ? null : bigDecimal.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
