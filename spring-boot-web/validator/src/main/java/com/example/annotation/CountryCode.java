package com.example.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.validator.CountryCodeValidator;

@Documented
@Constraint(validatedBy = CountryCodeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryCode {

    String message() default "Invalid CountryCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean notEmpty() default false;
}
