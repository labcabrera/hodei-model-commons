package com.github.labcabrera.hodei.model.commons.actions;

import java.time.LocalDateTime;
import java.util.Collection;

import com.github.labcabrera.hodei.model.commons.MessageEntry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(description = "Returns the information associated with the execution of an operation.")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationResult<T> {

	@Schema(description = "Execution result code")
	private String code;

	@Schema(description = "Execution result message")
	private String message;

	@Schema(description = "Execution date")
	private LocalDateTime timestamp;

	@Schema(description = "Additional execution information")
	private Collection<MessageEntry> messages;

	@Schema(description = "Execution result payload")
	private T payload;

}