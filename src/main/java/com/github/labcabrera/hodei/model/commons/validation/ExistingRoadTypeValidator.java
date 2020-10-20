package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.geo.RoadType;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingRoadType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingRoadTypeValidator implements ConstraintValidator<ExistingRoadType, String> {

	@Autowired(required = false)
	private CrudRepository<RoadType, String> roadTypeRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (roadTypeRepository == null) {
			log.warn("No road type repository bean has been defined. Ignoring validation");
			return true;
		}
		if (!roadTypeRepository.existsById(value)) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.road-type.unknown.message}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}