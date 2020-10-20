package com.github.labcabrera.hodei.model.commons.annotations;

/**
 * Generalizacion de las las entidades que tienen un identificador de tipo <i>String</i>.
 * 
 * @author CNP Partners Architecture
 * @since 1.0.0
 */
public interface HasId {

	String getId();

	void setId(String id);

}
