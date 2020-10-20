package org.github.labcabrera.hodei.model.commons.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.github.labcabrera.hodei.model.commons.validation.ExistingProvinceValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistingProvinceValidator.class)
public @interface ExistingProvince {

	String message() default "invalid.province";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}