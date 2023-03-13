package it.academy.fitness_studio.core.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    private static final String EMAIL_PATTERN = "([A-Za-z]+) ([A-Za-z]+)|([А-Яа-я]+ [А-Яа-я]+)";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext context) {
        return (validateName(username));
    }

    private boolean validateName(final String name) {
        Matcher matcher = PATTERN.matcher(name);
        return matcher.matches();
    }
}
