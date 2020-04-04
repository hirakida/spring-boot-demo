package com.example.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ METHOD, FIELD, PARAMETER, TYPE_USE })
@Constraint(validatedBy = CountryCodeValidator.class)
public @interface CountryCode {

    String message() default "Invalid CountryCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean notEmpty() default false;
}
