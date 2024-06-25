package de.sample.schulung.accounts.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// Composite Constraint
// see: https://www.baeldung.com/java-bean-validation-constraint-composition
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { })
@Pattern(regexp = "active|locked|disabled")
@ReportAsSingleViolation // use custom message()
public @interface CustomerStateString {

  String message() default "Not a valid state string.";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

}
