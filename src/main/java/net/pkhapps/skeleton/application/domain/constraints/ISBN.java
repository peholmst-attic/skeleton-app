package net.pkhapps.skeleton.application.domain.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO Document me!
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ISBNConstraintValidator.class)
public @interface ISBN {

    String message() default "Please enter a valid ISBN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
