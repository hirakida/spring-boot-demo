package com.example;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Documented
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Pattern(regexp = "\\d{2,4}-\\d{2,4}-\\d{4}")
public @interface TelNo {
    String message() default "Invalid TelNo.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
