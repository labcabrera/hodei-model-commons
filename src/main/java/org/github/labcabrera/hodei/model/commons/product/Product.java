package org.github.labcabrera.hodei.model.commons.product;

import java.util.List;

import org.github.labcabrera.hodei.model.commons.annotations.HasAuthorization;
import org.github.labcabrera.hodei.model.commons.annotations.HasId;
import org.github.labcabrera.hodei.model.commons.annotations.HasMetadata;
import org.github.labcabrera.hodei.model.commons.audit.EntityMetadata;
import org.github.labcabrera.hodei.model.commons.serialization.RoleManagerFilter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "products")
@Schema(description = "Product data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = { "id", "type", "name" })
public class Product implements HasId, HasAuthorization, HasMetadata {

	@Id
	@Schema(description = "Product internal identifier", required = true, example = "5be9b2f5d3a35838f056a318")
	private String id;

	@Schema(description = "Product type", example = "Sample product type")
	private String type;

	@Schema(description = "Product name", example = "Sample product")
	private String name;

	@Schema(description = "The application where this product is managed (HOST, AS400, CNP.NET, ...)", example = "AS400")
	private String platform;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private EntityMetadata metadata;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private List<String> authorization;

}