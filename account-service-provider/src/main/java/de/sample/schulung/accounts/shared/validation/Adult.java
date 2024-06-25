package de.sample.schulung.accounts.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// Custom Validator
// see: https://dzone.com/articles/create-your-own-constraint-with-bean-validation-20
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {

  String message() default "Must be an adult.";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

}
