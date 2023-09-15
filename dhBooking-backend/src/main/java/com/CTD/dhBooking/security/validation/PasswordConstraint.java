package com.CTD.dhBooking.security.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
   String message() default "ERROR: password must be at least 8 characters long and have 1 uppercase, 1 lowercase, 1 number and one symbol.";
   Class<?>[] groups() default {};
   Class<? extends Payload[]>[] payload() default {};
   String regexp();
}
