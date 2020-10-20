package com.github.labcabrera.hodei.model.commons.customer;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.labcabrera.hodei.model.commons.ContactData;
import com.github.labcabrera.hodei.model.commons.annotations.HasAuthorization;
import com.github.labcabrera.hodei.model.commons.annotations.HasId;
import com.github.labcabrera.hodei.model.commons.annotations.HasMetadata;
import com.github.labcabrera.hodei.model.commons.annotations.HasState;
import com.github.labcabrera.hodei.model.commons.audit.EntityMetadata;
import com.github.labcabrera.hodei.model.commons.geo.Address;
import com.github.labcabrera.hodei.model.commons.product.ProductReference;
import com.github.labcabrera.hodei.model.commons.serialization.RoleManagerFilter;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingCountry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customers")
@Schema(description = "Customer information")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements HasId, HasAuthorization, HasMetadata, HasState {

	@Id
	@Schema(description = "Person identifier", required = true, example = "1")
	protected String id;

	@Valid
	@NotNull(message = "{validation.constraints.abstract-entity.required-id-card}")
	@Schema(description = "Indicates the identification document", required = true)
	protected IdCard idCard;

	@NotBlank(message = "{validation.constraints.person.expected-name}")
	@Schema(description = "Name", required = true, example = "John")
	private String name;

	@NotBlank(message = "{validation.constraints.person.expected-surname1}")
	@Schema(description = "First surname", required = true, example = "Doe")
	private String surname1;

	@Schema(description = "Second surname", required = false, example = "Smith")
	private String surname2;

	@NotNull(message = "{validation.constraints.person.expected-birth-date}")
	@Past
	@Schema(description = "Birth date", required = true, example = "1977-11-03")
	private LocalDate birth;

	@ExistingCountry
	@Schema(description = "Birth country for this persons", required = true, example = "ESP")
	private String birthCountryId;

	@ExistingCountry
	@Schema(description = "Nationalities for this person. It is a list because a person can have several")
	private List<String> nationalities;

	@Valid
	@NotNull(message = "{validation.constraints.abstract-entity.required-fiscal-address}")
	@Schema(description = "Fiscal address for this person. It is unique accross the policies", required = true)
	private Address fiscalAddress;

	@NotNull(message = "{validation.constraints.person.expected-civil-status}")
	@Schema(description = "Civil status", required = false, example = "single")
	private CivilStatus civilStatus;

	@NotNull(message = "{validation.constraints.person.expected-gender}")
	@Schema(description = "Gender", required = true, example = "male")
	private Gender gender;

	@Valid
	@NotNull(message = "{validation.constraints.abstract-entity.required-contact-data}")
	@Schema(description = "Contact data. Phones, fax, emails and websites", required = true)
	protected ContactData contactData;

	@Valid
	@Schema(description = "Profession information")
	protected CustomerProfessionInfo professionInfo;

	@Schema(description = "Product reference list", required = true)
	private List<ProductReference> productReferences;

	@Schema(description = "Entity state identifier", example = "active")
	private String state;

	@Schema(description = "Entity metadata")
	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private EntityMetadata metadata;

	@Schema(description = "Entity authorization")
	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private List<String> authorization;
}
