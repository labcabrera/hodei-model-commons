package com.github.labcabrera.hodei.model.commons.customer;

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

	@ExistingJobType
	@Schema(description = "Job Type. Available values can be obtained through customers API", required = false)
	private String jobTypeId;

	@ExistingProfession
	@Schema(description = "Profession. Available values can be obtained through customers API", required = false)
	private String professionId;

	@Schema(description = "Economic activity. Available values can be obtained through customers API", required = false)
	private String economicActivityId;
}
