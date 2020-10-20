package com.github.labcabrera.hodei.model.commons.geo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Locality {

	@Id
	private String id;

	private String name;

	private String zipCode;

	private String provinceId;

}
