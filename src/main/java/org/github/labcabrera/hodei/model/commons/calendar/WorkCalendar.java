package org.github.labcabrera.hodei.model.commons.calendar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.github.labcabrera.hodei.model.commons.EntityMetadata;
import org.github.labcabrera.hodei.model.commons.HasId;
import org.github.labcabrera.hodei.model.commons.HasMetadata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "workCalendars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkCalendar implements HasId, HasMetadata {

	@Id
	private String id;

	@NotBlank
	private String name;

	@NotNull
	private Integer year;

	private EntityMetadata metadata;

}
