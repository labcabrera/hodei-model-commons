package com.github.labcabrera.hodei.model.commons.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.github.labcabrera.hodei.model.commons.geo.Address;
import com.github.labcabrera.hodei.model.commons.geo.Country;
import com.github.labcabrera.hodei.model.commons.geo.Province;

@ContextConfiguration
@SpringBootTest
public class AddressValidationTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValid() {
		Address address = buildValidAddress();
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidZipCode() {
		Address address = buildValidAddress();
		address.setZipCode("280001");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidLocality() {
		Address address = buildValidAddress();
		address.setLocality("Test123?");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidCountry() {
		Address address = buildValidAddress();
		address.setCountryId("XXX");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(2, violations.size());
	}

	@Test
	public void testInvalidProvince() {
		Address address = buildValidAddress();
		address.setProvinceId("ESP-XX");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidProvinceCountry() {
		Address address = buildValidAddress();
		address.setCountryId("ITA");
		address.setProvinceId("ESP-28");
		Set<ConstraintViolation<Address>> violations = validator.validate(address);
		assertEquals(1, violations.size());
	}

	private Address buildValidAddress() {
		return Address.builder()
			.countryId("ESP")
			.provinceId("ESP-28")
			.locality("Madrid")
			.zipCode("28013")
			.road("Calle Mayor 2, 3ºB")
			.build();
	}

	@Configuration
	@SuppressWarnings("unchecked")
	public static class PersonValidationTestConfig {

		@Bean
		Validator validator() {
			return new LocalValidatorFactoryBean();
		}

		@Bean
		CrudRepository<Country, String> countryRepository() {
			CrudRepository<Country, String> mock = mock(CrudRepository.class);
			when(mock.existsById("ESP")).thenReturn(true);
			when(mock.existsById("ITA")).thenReturn(true);
			return mock;
		}

		@Bean
		CrudRepository<Province, String> provinceRepository() {
			CrudRepository<Province, String> mock = mock(CrudRepository.class);
			when(mock.existsById("ESP-28")).thenReturn(true);
			when(mock.findById("ESP-28")).thenReturn(Optional.of(Province.builder()
				.countryId("ESP")
				.build()));
			return mock;
		}

	}

}