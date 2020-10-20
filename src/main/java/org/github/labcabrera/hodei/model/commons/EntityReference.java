package org.github.labcabrera.hodei.model.commons;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityReference {

	@Schema(description = "Entity type (person or legal entity)", required = true, example = "person")
	@NotNull
	@NotBlank
	private String type;

	@Schema(description = "Entity identifier", required = true, example = "5c73fffc4b20797ad8e93195")
	@NotNull
	@NotBlank
	private String reference;

}
