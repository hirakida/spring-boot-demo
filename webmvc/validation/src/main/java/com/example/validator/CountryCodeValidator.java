package com.example.validator;

import java.util.Arrays;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.example.annotation.CountryCode;

public class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {
    private boolean notEmpty;

    @Override
    public void initialize(CountryCode countryCode) {
        notEmpty = countryCode.notEmpty();
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (notEmpty && StringUtils.isEmpty(code)) {
            return true;
        }
        return Arrays.asList(Locale.getISOCountries()).contains(code);
    }
}
