package org.github.labcabrera.hodei.model.commons;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "currencies")
@Schema(description = "Represents a currency. It can be EUR, GBP, USD, etc.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = "id")
public class Currency {

	@Id
	@Schema(description = "Internal identifier for the currency using ISO 4217 codes ", example = "EUR")
	private String id;

	@Schema(description = "The name/description for this currency", example = "Euro")
	private String name;

	private Integer scale;

}
