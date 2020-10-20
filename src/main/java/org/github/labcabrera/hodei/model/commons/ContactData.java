package org.github.labcabrera.hodei.model.commons;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Contact data")
public class ContactData {

	@Schema(description = "Email for this person or company", example = "johndoesmith@mail.org")
	private String email;

	@Schema(description = "Alternate email for this person or company", example = "johndoe@mail.org")
	private String alternateEmail;

	@Schema(description = "Cell phone for this person or company", example = "600700800")
	private String cellPhone;

	@Schema(description = "Landline phone for this person or company", example = "+34 910001122")
	private String landlinePhone;

	@Schema(description = "Fax for this person or company", example = "910002233")
	private String fax;

	@Schema(description = "Contact web page for this person or company", example = "https://www.sample.org/")
	private String website;

}