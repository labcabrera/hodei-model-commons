package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.product.Network;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingNetwork;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingNetworkValidator implements ConstraintValidator<ExistingNetwork, String> {

	@Autowired(required = false)
	private CrudRepository<Network, String> networkRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (networkRepository == null) {
			log.warn("No network repository bean has been defined. Ignoring validation");
			return true;
		}
		else if (!networkRepository.existsById(value)) {
			context.buildConstraintViolationWithTemplate("{validation.constraints.network.unknown}").addConstraintViolation();
			return false;
		}
		return true;
	}

}
