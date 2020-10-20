package com.github.labcabrera.hodei.model.commons.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.labcabrera.hodei.model.commons.validation.IdCardValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
public @interface ValidIdCard {

	String message() default "invalid.idCard";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
