package com.github.labcabrera.hodei.model.commons.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.github.labcabrera.hodei.model.commons.ContactData;
import com.github.labcabrera.hodei.model.commons.customer.Customer;
import com.github.labcabrera.hodei.model.commons.customer.Gender;
import com.github.labcabrera.hodei.model.commons.customer.IdCard;
import com.github.labcabrera.hodei.model.commons.customer.IdCardType;
import com.github.labcabrera.hodei.model.commons.geo.Address;
import com.github.labcabrera.hodei.model.commons.geo.Country;
import com.github.labcabrera.hodei.model.commons.geo.Province;

@ContextConfiguration
@SpringBootTest
public class PersonValidationTest {

	@Autowired
	private Validator validator;

	@Test
	public void testValid() {
		Customer customer = buildValidCustomer();
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testValidPatternName01() {
		Customer customer = buildValidCustomer();
		customer.setName("John ñÑ áéíóú ÁÉÍÓÓ");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testValidPatternName02() {
		Customer customer = buildValidCustomer();
		customer.setName("John àèìòù ÀÈÌÒÙ üÜ");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testValidPatternName03() {
		Customer customer = buildValidCustomer();
		customer.setName("John-Çç");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidNamePatternName01() {
		Customer customer = buildValidCustomer();
		customer.setName("Jo?n");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidFiscalAddressCountryId() {
		Customer customer = buildValidCustomer();
		customer.getFiscalAddress().setCountryId("XXX");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidBirthCountrysId() {
		Customer customer = buildValidCustomer();
		customer.setBirthCountryId("XXX");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidNationalitiesEmptu() {
		Customer customer = buildValidCustomer();
		customer.setNationalities(new ArrayList<>());
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testValidNationalitiesCountryId() {
		Customer customer = buildValidCustomer();
		customer.setNationalities(Arrays.asList("ESP", "ITA"));
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidNationalitiesCountryId() {
		Customer customer = buildValidCustomer();
		customer.setNationalities(Arrays.asList("ESP", "XXX"));
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidProvince() {
		Customer customer = buildValidCustomer();
		customer.getFiscalAddress().setProvinceId("ESP-XX");
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidEmail() {
		Customer customer = buildValidCustomer();
		customer.setContactData(ContactData.builder().email("invalid-email@").build());
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidPhoneNumber01() {
		Customer customer = buildValidCustomer();
		customer.setContactData(ContactData.builder()
			.phoneNumber("666777888")
			.alternatePhoneNumber("+34 555666777")
			.build());
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidPhoneNumber01() {
		Customer customer = buildValidCustomer();
		customer.setContactData(ContactData.builder()
			.phoneNumber("xxx 666 yyy")
			.alternatePhoneNumber("111222")
			.build());
		Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
		assertEquals(2, violations.size());
	}

	private Customer buildValidCustomer() {
		return Customer.builder()
			.name("John")
			.surname1("Doe")
			.idCard(IdCard.builder()
				.number("11111111H")
				.type(IdCardType.NIF)
				.build())
			.fiscalAddress(Address.builder()
				.countryId("ESP")
				.provinceId("ESP-28")
				.locality("Madrid")
				.zipCode("28013")
				.road("Calle Mayor 2, 3ºB")
				.build())
			.birth(LocalDate.parse("1980-08-30"))
			.gender(Gender.MALE)
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
			return mock;
		}

	}

}