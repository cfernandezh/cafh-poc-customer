package com.cafh.poc.customer.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ValidAgeValidator.class)
@Target( {ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface ValidAge {

    String message() default "The age is incorrect based in the birthday";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
