package org.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.github.labcabrera.hodei.model.commons.geo.Province;
import org.github.labcabrera.hodei.model.commons.validation.annotation.ExistingProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ExistingProvinceValidator implements ConstraintValidator<ExistingProvince, String> {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		if (!mongoTemplate.exists(new Query(Criteria.where("id").is(value)), Province.class)) {
			ctx.buildConstraintViolationWithTemplate("{validation.constraints.province.unknown.message}")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}