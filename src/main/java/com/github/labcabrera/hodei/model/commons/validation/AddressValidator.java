package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.labcabrera.hodei.model.commons.geo.Address;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ValidAddress;

public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {

	@Override
	public boolean isValid(Address address, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		boolean result = true;
		result &= validateProvince(address, ctx);
		return result;
	}

	// Solo exigimos la provincia para ESP. Seria mejor parametrizar la lista de paises
	private boolean validateProvince(Address address, ConstraintValidatorContext ctx) {
		boolean requiredProvince = "ESP".equals(address.getCountryId());
		boolean hasProvince = address.getProvinceId() != null;
		if (requiredProvince && !hasProvince) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.address.required-province.message}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}