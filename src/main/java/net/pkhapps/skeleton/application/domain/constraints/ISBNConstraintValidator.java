package net.pkhapps.skeleton.application.domain.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * TODO Document me!
 */
public class ISBNConstraintValidator implements ConstraintValidator<ISBN, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
