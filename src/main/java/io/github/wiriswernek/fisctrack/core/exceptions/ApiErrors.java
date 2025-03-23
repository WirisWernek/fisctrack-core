package io.github.wiriswernek.fisctrack.core.exceptions;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(String message) {
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }
}
