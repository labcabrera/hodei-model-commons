package com.github.labcabrera.hodei.model.commons.customer;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingJobType;
import com.github.labcabrera.hodei.model.commons.validation.annotation.ExistingProfession;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfessionInfo {

	@DBRef
	@ExistingJobType
	@Schema(description = "Job Type. Available values can be obtained through customers API", required = false)
	private JobType jobType;

	@DBRef
	@ExistingProfession
	@Schema(description = "Profession. Available values can be obtained through customers API", required = false)
	private Profession profession;

	@Schema(description = "Economic activity. Available values can be obtained through customers API", required = false)
	private String economicActivityId;
}
