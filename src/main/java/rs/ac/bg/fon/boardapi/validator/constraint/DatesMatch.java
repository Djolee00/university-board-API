package rs.ac.bg.fon.boardapi.validator.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import rs.ac.bg.fon.boardapi.validator.DatesMatchValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DatesMatchValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DatesMatch {

    String message() default "Invalid date request";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
