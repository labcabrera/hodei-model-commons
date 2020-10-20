package com.github.labcabrera.hodei.model.commons.product;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.labcabrera.hodei.model.commons.EntityReference;
import com.github.labcabrera.hodei.model.commons.annotations.HasAuthorization;
import com.github.labcabrera.hodei.model.commons.annotations.HasId;
import com.github.labcabrera.hodei.model.commons.annotations.HasMetadata;
import com.github.labcabrera.hodei.model.commons.audit.EntityMetadata;
import com.github.labcabrera.hodei.model.commons.serialization.RoleManagerFilter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "agreements")
@Schema(description = "Establishes particular conditions for a specific product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = { "id" })
public class Agreement implements HasId, HasAuthorization, HasMetadata {

	@Id
	@Schema(description = "Agreement identifier", required = true, example = "23001")
	private String id;

	@Schema(description = "Agreement name", example = "SIPEN A.M. 23001")
	private String name;

	@Schema(description = "Product associated to this agreement", required = true)
	private String productId;

	@Schema(description = "Legal entity associated with the framework agreement")
	private EntityReference company;

	@Schema(description = "Selling channel", example = "MEDIATION", required = false)
	private String sellingChannelId;

	@Schema(description = "Sub-selling channel")
	private String subSellingChannel;

	@Schema(description = "Start date for contracting this agreement", example = "1999-02-20T00:00:00.000Z")
	private LocalDate start;

	@Schema(description = "Indicates when the agreement is no longer avaiable for contracting", example = "2020-03-20T00:00:00.000Z")
	private LocalDate end;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	@Schema(description = "Entity metadata")
	private EntityMetadata metadata;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	@Schema(description = "Entity authorization")
	private List<String> authorization;

}
