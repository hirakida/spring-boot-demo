package com.example;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Locale;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;

/**
 * validatorのannotation
 */
@Documented
@Constraint(validatedBy = CountryCode.CountryCodeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryCode {

    String message() default "Invalid CountryCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // annotationに引数を追加する
    boolean notEmpty() default false;

    /**
     * validator
     */
    class CountryCodeValidator implements ConstraintValidator<CountryCode, String> {
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
}
