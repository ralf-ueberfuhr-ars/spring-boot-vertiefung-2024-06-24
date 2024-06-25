package de.sample.schulung.accounts.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

  @Override
  public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
    if(null == value) {
      return true;
    }
    var currentDate = LocalDate.now();
    var currentDateBefore18Years = currentDate.minusYears(18);
    return value.isBefore(currentDateBefore18Years) || value.isEqual(currentDateBefore18Years);
  }
}
