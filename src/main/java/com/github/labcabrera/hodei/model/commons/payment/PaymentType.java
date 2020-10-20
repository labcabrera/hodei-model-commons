package com.github.labcabrera.hodei.model.commons.payment;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Representa el tipo de pago a realizar en una aportacion.
 * 
 * @author CNP Partners Architecture
 * @since 1.0.0
 */
public enum PaymentType {

	/** Domiciliacion bancaria */
	DIRECT_DEBIT("direct-debit"),

	/** Transferencia */
	TRANSFER("transfer"),

	/** Cheque. */
	CHEQUE("cheque"),

	/** Traspaso */
	REINVESTMENT("reinvestment"),

	MANUAL("manual"),

	OTHER("other");

	private String value;

	private PaymentType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}
}
