package com.example.validator;

import java.util.List;
import java.util.Locale;

import org.springframework.util.ObjectUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {
    private boolean notEmpty;

    @Override
    public void initialize(CountryCode countryCode) {
        notEmpty = countryCode.notEmpty();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (notEmpty && ObjectUtils.isEmpty(code)) {
            return false;
        }
        return List.of(Locale.getISOCountries()).contains(code);
    }
}
