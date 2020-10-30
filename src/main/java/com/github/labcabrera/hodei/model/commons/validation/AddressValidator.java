package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.geo.Address;
import com.github.labcabrera.hodei.model.commons.geo.Province;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ValidAddress;

public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {

	private static final String ESP_ZIPCODE_PATTERN = "^\\d{5}$";

	@Autowired(required = false)
	private CrudRepository<Province, String> provinceRepository;

	@Override
	public boolean isValid(Address address, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		boolean result = true;
		result &= checkProvinceCountry(address, ctx);
		result &= checkSpainAddress(address, ctx);
		return result;
	}

	private boolean checkProvinceCountry(Address address, ConstraintValidatorContext ctx) {
		if (address.getProvince() == null || provinceRepository == null || address.getCountry() == null) {
			return true;
		}
		Province province = provinceRepository.findById(address.getProvince().getId()).orElse(null);
		if (province != null && !address.getCountry().getId().equals(province.getCountryId())) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.address.province-does-not-match-country}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

	private boolean checkSpainAddress(Address address, ConstraintValidatorContext ctx) {
		if (address.getCountry() == null || !"ESP".equals(address.getCountry().getId())) {
			return true;
		}
		if (address.getProvince() == null) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.address.required-province}")
				.addConstraintViolation();
			return false;
		}
		if (address.getZipCode() == null) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.address.required-zipcode}")
				.addConstraintViolation();
			return false;
		}
		else if (!address.getZipCode().matches(ESP_ZIPCODE_PATTERN)) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.address.invalid-zip-code}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}