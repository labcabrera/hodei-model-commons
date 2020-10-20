package com.github.labcabrera.hodei.model.commons.actions;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.labcabrera.hodei.model.commons.annotations.HasAuthorization;
import com.github.labcabrera.hodei.model.commons.annotations.HasId;
import com.github.labcabrera.hodei.model.commons.serialization.RoleManagerFilter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "operationExecutions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionExecution implements HasId, HasAuthorization {

	@Id
	@Schema(description = "Action execution identifier", required = true, example = "5be9b2f5d3a35838f056a318")
	private String id;

	@NotEmpty
	private String module;

	private String parentId;

	@NotEmpty
	@Schema(description = "Specifies the type of change", required = true, example = "regular-payment-update")
	private String actionType;

	@Schema(description = "Creation date", required = true, example = "2015-01-01T05:12:55.000Z")
	private LocalDateTime created;

	private LocalDateTime executed;

	@Schema(description = "Additional information for this change", required = true, example = "Regular payment modification")
	@NotBlank
	private String description;

	private String actionData;

	private ActionExecutionUser user;

	//TODO consider change to OperationResult
	private ActionExecutionResult result;

	@NotBlank
	@Schema(description = "Related entity type", required = true, example = "policy")
	private String entityType;

	@NotBlank
	@Schema(description = "Related entity identifier", required = true, example = "5be9b2f5d3a35838f056a318")
	private String entityId;

	@NotNull
	@JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = RoleManagerFilter.class)
	@Schema(description = "Authorization list")
	private List<String> authorization;

}
