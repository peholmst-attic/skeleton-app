package net.pkhapps.skeleton.application.domain.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for documentation purposes only to indicate that a default entity constructor is intended to be used
 * by JPA only and not in application code.
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)
@SuppressWarnings("unused")
public @interface DefaultJpaConstructor {
}
