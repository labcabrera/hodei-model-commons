package com.github.labcabrera.hodei.model.commons.payment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Indicates the account type")
public enum AccountType {

	IBAN, CCC, RIB;

}