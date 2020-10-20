package com.github.labcabrera.hodei.model.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.github.labcabrera.hodei.model.commons.customer.JobType;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingJobType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistingJobTypeValidator implements ConstraintValidator<ExistingJobType, String> {

	@Autowired(required = false)
	private CrudRepository<JobType, String> jobTypeRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (value == null) {
			return true;
		}
		else if (jobTypeRepository == null) {
			log.warn("No job type repository bean has been defined. Ignoring validation");
			return true;
		}
		else if (!jobTypeRepository.existsById(value)) {
			context.buildConstraintViolationWithTemplate("{validation.constraints.job-type.unknown}").addConstraintViolation();
			return false;
		}
		return true;
	}

}
