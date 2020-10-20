package org.github.labcabrera.hodei.model.commons.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Represents a bank account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Account {

	@Schema(description = "Account number", required = true, example = "ES7620770024003102575766")
	private String number;

	@Schema(description = "Account type", example = "IBAN")
	private AccountType type;

	@Schema(description = "Name and surname for this account owner", example = "JOHN DOE SMITH")
	private String holder;

	@Schema(description = "Account BIC", required = false)
	private String bic;

}
