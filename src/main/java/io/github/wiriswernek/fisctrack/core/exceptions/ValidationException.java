package io.github.wiriswernek.fisctrack.core.exceptions;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }

    public static <T> ValidationException generateMessage(Set<ConstraintViolation<T>> violations) {
        var messages = violations.stream().map(ConstraintViolation::getMessage).toList();
        return new ValidationException(String.join(";", messages));
    }


}
