package org.github.labcabrera.hodei.model.commons.audit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.github.labcabrera.hodei.model.commons.MessageEntry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityMetadata {

	@Schema(description = "Entity creation time")
	private LocalDateTime created;

	@Schema(description = "Entity modification time")
	private LocalDateTime updated;

	@Schema(description = "Entity source", example = "hodei-customers-worker")
	private String source;

	@Schema(description = "Synchronization information with external systems")
	private Map<String, SynchronizationInfo> sync;

	@Schema(description = "Messages associated with the entity")
	private List<MessageEntry> messages;

}
