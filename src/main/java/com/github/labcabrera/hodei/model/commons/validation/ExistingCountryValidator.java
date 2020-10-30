package com.github.labcabrera.hodei.model.commons.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.geo.Country;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingCountry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingCountryValidator implements ConstraintValidator<ExistingCountry, Object> {

	@Autowired(required = false)
	private CrudRepository<Country, String> countryRepository;

	@Override
	@SuppressWarnings("unchecked")
	public boolean isValid(Object object, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if (object == null) {
			return true;
		}
		else if (countryRepository == null) {
			log.warn("No country repository bean has been defined. Ignoring validation");
			return true;
		}
		else if (object instanceof Country) {
			Country country = (Country) object;
			if (country.getId() == null) {
				ctx.buildConstraintViolationWithTemplate("{validation.constraints.country.required}")
					.addConstraintViolation();
				return false;
			}
			return validate(country.getId(), ctx);
		}
		else if (object instanceof String) {
			return validate((String) object, ctx);
		}
		else if (object instanceof List) {
			List<String> list = (List<String>) object;
			boolean valid = true;
			for (String i : list) {
				valid &= validate(i, ctx);
			}
			return valid;
		}

		ctx.buildConstraintViolationWithTemplate("{validation.constraints.country.invalid-type}")
			.addConstraintViolation();
		return false;
	}

	private boolean validate(String value, ConstraintValidatorContext ctx) {
		if (!countryRepository.existsById(value)) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.country.unknown}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}