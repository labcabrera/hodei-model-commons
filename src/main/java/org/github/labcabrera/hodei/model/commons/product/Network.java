package org.github.labcabrera.hodei.model.commons.product;

import java.util.List;

import org.github.labcabrera.hodei.model.commons.ContactData;
import org.github.labcabrera.hodei.model.commons.EntityReference;
import org.github.labcabrera.hodei.model.commons.annotations.HasAuthorization;
import org.github.labcabrera.hodei.model.commons.annotations.HasId;
import org.github.labcabrera.hodei.model.commons.annotations.HasMetadata;
import org.github.labcabrera.hodei.model.commons.annotations.HasState;
import org.github.labcabrera.hodei.model.commons.audit.EntityMetadata;
import org.github.labcabrera.hodei.model.commons.geo.Address;
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

@Document(collection = "networks")
@Schema(description = "Distributor data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(of = "id")
public class Network implements HasId, HasAuthorization, HasMetadata, HasState {

	@Id
	@Schema(description = "Distributor internal ID", required = true, example = "5be9b2f5d3a35838f056a318")
	private String id;

	@Schema(description = "Office code", required = true, example = "324")
	private String office;

	@Schema(description = "Name for this office/mediator", example = "Barclays Castellana 121")
	private String name;

	@Schema(description = "Legal entity to which it is associated")
	private EntityReference entityRef;

	@Schema(description = "Postal address associated")
	private Address postalAddress;

	@Schema(description = "Selling channel (Spanish names : MEDIACION, FINANCIERO, GRANDES_CUENTAS, BANCASEGUROS, EMPLEADOS)", example = "MEDIATION")
	private String sellingChannelId;

	@Schema(description = "DGS code", example = "C0007")
	private String dgsCode;

	@Schema(description = "Zone", example = "EAST")
	private String zone;

	@Schema(description = "Contact data. Phones, fax, emails and websites")
	private ContactData contactData;

	private String parentNetworkId;

	private String state;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private EntityMetadata metadata;

	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	private List<String> authorization;

}
