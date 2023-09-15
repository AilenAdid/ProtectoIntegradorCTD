package com.CTD.dhBooking.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class OnwardsValidator implements ConstraintValidator<Onwards, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        LocalDate today = LocalDate.now();
        return today.isBefore(value) || today.isEqual(value);
    }
}