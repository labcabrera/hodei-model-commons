package com.github.labcabrera.hodei.model.commons.serialization;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class RoleManagerFilter {

	private static final String REQUIRED_ROLE = "role-extended-model";

	@Override
	public boolean equals(Object obj) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.filter(REQUIRED_ROLE::equals)
			.count() == 0L;
	}

	@Override
	public int hashCode() {
		return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
			? SecurityContextHolder.getContext().getAuthentication().hashCode()
			: 0;
	}
}