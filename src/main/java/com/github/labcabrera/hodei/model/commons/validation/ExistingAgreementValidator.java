package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.product.Agreement;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingAgreement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingAgreementValidator implements ConstraintValidator<ExistingAgreement, String> {

	@Autowired(required = false)
	private CrudRepository<Agreement, String> agreementRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (agreementRepository == null) {
			log.warn("No agreement repository bean has been defined. Ignoring validation");
			return true;
		}
		else if (!agreementRepository.existsById(value)) {
			context.buildConstraintViolationWithTemplate("{validation.constraints.agreement.unknown}").addConstraintViolation();
			return false;
		}
		return true;
	}

}
