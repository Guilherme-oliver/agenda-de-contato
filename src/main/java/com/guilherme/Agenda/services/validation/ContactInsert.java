package com.guilherme.Agenda.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContactInsertValidation.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactInsert {

    String message() default "Validation error!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
