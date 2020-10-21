package com.github.labcabrera.hodei.model.commons.validation;

public final class ValidationPattern {

	public static final String NAME_DEFAULT = "^[A-Za-zñÑçÇáéíóúàèìòùüÁÉÍÓÚÀÈÌÒÙÜ'][\\s\\-A-Za-zñÑçÇáéíóúàèìòùüÁÉÍÓÚÀÈÌÒÙÜ']+[A-Za-zñÑçÇáéíóúàèìòùüÁÉÍÓÚÀÈÌÒÙÜ']$";

	public static final String PHONE_NUMBER_DEFAULT = "^(\\+\\d{2,3} )?\\d{9}$";

	public static final String WEBSITE_DEFAULT = "^https?:\\/\\/[-a-zA-Z0-9+&@#\\/%?=~_|!:,.;]*[-a-z\\/]$";

	private ValidationPattern() {
	}
}
