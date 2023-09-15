package com.CTD.dhBooking.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint, String> {
   private String regexp;

   @Override
   public void initialize(PasswordConstraint passwordConstraint){
      regexp = passwordConstraint.regexp();
   }

   @Override
   public boolean isValid(String password, ConstraintValidatorContext context){
      if (password == null){
         return false;
      }
      return password.matches(regexp);
   }
}
