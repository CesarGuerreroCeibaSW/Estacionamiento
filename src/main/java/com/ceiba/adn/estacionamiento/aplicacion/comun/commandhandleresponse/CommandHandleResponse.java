package com.ceiba.adn.estacionamiento.aplicacion.comun.commandhandleresponse;

import org.springframework.transaction.annotation.Transactional;

public interface CommandHandleResponse<C, R> {

	@Transactional
	R exec(C command);
}
