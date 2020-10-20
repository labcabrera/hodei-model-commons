package com.github.labcabrera.hodei.model.commons.customer;

import com.fasterxml.jackson.annotation.JsonValue;

public enum IdCardType {

	NIF("nif"),

	NIE("nie"),

	CIF("cif"),

	PASSPORT("passport"),

	OTHER("other");

	private String value;

	private IdCardType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}