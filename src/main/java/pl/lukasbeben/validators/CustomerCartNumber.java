package pl.lukasbeben.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerCartNumberValidator.class)
public @interface CustomerCartNumber {
    String message() default "Podany numer karty już istnieje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
