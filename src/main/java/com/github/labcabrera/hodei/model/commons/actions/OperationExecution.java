package com.github.labcabrera.hodei.model.commons.actions;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collation = "actionResults")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationExecution<E> {

	@Id
	private String id;

	@NotNull
	private LocalDateTime created;

	@NotNull
	private String state;

	private E payload;

}
