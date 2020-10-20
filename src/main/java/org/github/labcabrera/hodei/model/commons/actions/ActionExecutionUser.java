package org.github.labcabrera.hodei.model.commons.actions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionExecutionUser {

	private String username;

	private String authorities;

}
