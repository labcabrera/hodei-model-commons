package com.github.labcabrera.hodei.model.commons.annotations;

/**
 * Generalizacion de las entidades que estan asociadas a un estado.
 * 
 * @author CNP Partners Architecture
 * @since 1.0.0
 *
 * @param <T>
 */
public interface HasState extends HasId {

	String getState();

	void setState(String state);

}
