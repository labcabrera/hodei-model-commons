package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.customer.Profession;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingProfession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingProfessionValidator implements ConstraintValidator<ExistingProfession, String> {

	@Autowired(required = false)
	private CrudRepository<Profession, String> professionRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (professionRepository == null) {
			log.warn("No profession repository bean has been defined. Ignoring validation");
			return true;
		}
		else if (!professionRepository.existsById(value)) {
			context.buildConstraintViolationWithTemplate("{validation.constraints.profession.unknown}").addConstraintViolation();
			return false;
		}
		return true;
	}

}
