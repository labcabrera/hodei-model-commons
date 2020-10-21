package com.github.labcabrera.hodei.model.commons.customer;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommercialNotifications {

	NONE("none"),

	EMAIL("email"),

	POSTAL("postal");

	private String value;

	private CommercialNotifications(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}