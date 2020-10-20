package org.github.labcabrera.hodei.model.commons.calendar;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Holiday {

	@Id
	private String id;

	@NotBlank
	private String calendarId;

	@NotBlank
	private String name;

	@NotNull
	private LocalDate date;

	private String type;
}
