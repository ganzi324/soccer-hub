package com.ganzi.travelmate.common.validation;

import com.ganzi.travelmate.common.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 50;
    private static final String DEFAULT_REGEX = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{" +
            MIN_SIZE + "," + MAX_SIZE + "}$";

    private String pattern;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern().isEmpty() ? DEFAULT_REGEX : constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }

        return password.matches(pattern);
    }
}
