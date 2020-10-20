package com.github.labcabrera.hodei.model.commons.annotations;

import java.util.List;

/**
 * Generalizacion de las entidades que requieren de una lista de permisos de acceso.
 * 
 * @author CNP Partners Architecture
 * @since 1.0.0
 */
public interface HasAuthorization extends HasId {

	List<String> getAuthorization();

	void setAuthorization(List<String> permissions);

}
