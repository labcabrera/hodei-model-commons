package org.github.labcabrera.hodei.model.commons.validation;

import java.util.function.Predicate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.github.labcabrera.hodei.model.commons.customer.IdCard;
import org.github.labcabrera.hodei.model.commons.validation.annotation.ValidIdCard;
import org.github.labcabrera.hodei.model.commons.validation.idcard.CifValidator;
import org.github.labcabrera.hodei.model.commons.validation.idcard.NieValidator;
import org.github.labcabrera.hodei.model.commons.validation.idcard.NifValidator;

public class IdCardValidator implements ConstraintValidator<ValidIdCard, IdCard> {

	@Override
	public boolean isValid(IdCard value, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if (value.getNumber() == null) {
			return true;
		}
		if (value.getType() != null) {
			Predicate<String> predicate = resolveIdCardNumberPredicate(value);
			if (predicate != null && !predicate.test(value.getNumber())) {
				ctx.buildConstraintViolationWithTemplate("{validation.constraints.idcard-invalid.message}")
					.addPropertyNode("idCard.number")
					.addConstraintViolation();
				return false;
			}
		}
		return true;
	}

	private Predicate<String> resolveIdCardNumberPredicate(IdCard idCard) {
		switch (idCard.getType()) {
		case NIF:
			return new NifValidator();
		case CIF:
			return new CifValidator();
		case NIE:
			return new NieValidator();
		default:
			return null;
		}
	}

}