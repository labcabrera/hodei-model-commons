package com.github.labcabrera.hodei.model.commons.geo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.github.labcabrera.hodei.model.commons.validation.ValidationPattern;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingCountry;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingProvince;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingRoadType;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ValidAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Address data")
@ValidAddress
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

	@ExistingCountry
	@NotNull(message = "{validation.constraints.address.required-country}")
	@Schema(description = "Country", required = true, example = "ESP")
	private Country country;

	@ExistingProvince
	@Schema(description = "Province", required = false, example = "ESP-28")
	private Province province;

	@NotBlank(message = "{validation.constraints.address.required-locality}")
	@Schema(description = "Locality", required = true, example = "Madrid")
	@Pattern(regexp = ValidationPattern.NAME_DEFAULT)
	private String locality;

	@NotBlank(message = "{validation.constraints.address.required-zipcode}")
	@Schema(description = "Zip code", required = true, example = "28001")
	private String zipCode;

	@ExistingRoadType
	@Schema(description = "Road type. This value must match one of the existing values in the system", example = "street")
	private String roadType;

	@NotBlank(message = "{validation.constraints.address.required-road}")
	@Schema(description = "Street, number and floor", required = true, example = "Serrano 3, 2B")
	private String road;

}