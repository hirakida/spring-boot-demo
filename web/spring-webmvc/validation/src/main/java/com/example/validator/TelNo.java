package com.example.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, TYPE_USE })
@ReportAsSingleViolation
@Pattern(regexp = "\\d{2,4}.*\\d{2,4}.*\\d{4}")
@Constraint(validatedBy = {})
public @interface TelNo {
    String message() default "Invalid TelNo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
