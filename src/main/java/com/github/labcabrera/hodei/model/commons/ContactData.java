package com.github.labcabrera.hodei.model.commons;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.github.labcabrera.hodei.model.commons.validation.ValidationPattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Contact data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactData {

	@Email
	@Schema(description = "Email", example = "johndoesmith@mail.org")
	private String email;

	@Email
	@Schema(description = "Alternate email", example = "johndoe@mail.org")
	private String alternateEmail;

	@Pattern(regexp = ValidationPattern.PHONE_NUMBER_DEFAULT)
	@Schema(description = "Primary phone number", example = "600700800")
	private String phoneNumber;

	@Pattern(regexp = ValidationPattern.PHONE_NUMBER_DEFAULT)
	@Schema(description = "Alternate phone number", example = "+34 910001122")
	private String alternatePhoneNumber;

	@Pattern(regexp = ValidationPattern.PHONE_NUMBER_DEFAULT)
	@Schema(description = "Fax", example = "910002233")
	private String fax;

	@Pattern(regexp = ValidationPattern.WEBSITE_DEFAULT)
	@Schema(description = "Contact web page", example = "https://www.sample.org/")
	private String website;

}