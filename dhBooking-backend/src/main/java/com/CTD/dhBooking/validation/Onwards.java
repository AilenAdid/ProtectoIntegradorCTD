package com.CTD.dhBooking.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OnwardsValidator.class)
public @interface Onwards {
    String message() default "ERROR: date must be from current date on.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
