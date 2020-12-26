package com.example.validator;

import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.ObjectUtils;

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
