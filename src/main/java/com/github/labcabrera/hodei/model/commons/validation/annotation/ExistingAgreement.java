package com.github.labcabrera.hodei.model.commons.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.github.labcabrera.hodei.model.commons.validation.ExistingAgreementValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingAgreementValidator.class)
public @interface ExistingAgreement {

	String message() default "invalid.agreement";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}