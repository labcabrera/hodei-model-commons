package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.geo.Province;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingProvince;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingProvinceValidator implements ConstraintValidator<ExistingProvince, String> {

	@Autowired(required = false)
	private CrudRepository<Province, String> provinceRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (provinceRepository == null) {
			log.warn("No province repository bean has been defined. Ignoring validation");
			return true;
		}
		if (!provinceRepository.existsById(value)) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.province.unknown.message}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}