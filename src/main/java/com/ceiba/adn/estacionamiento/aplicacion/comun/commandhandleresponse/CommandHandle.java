package com.ceiba.adn.estacionamiento.aplicacion.comun.commandhandleresponse;

import org.springframework.transaction.annotation.Transactional;

public interface CommandHandle<C> {

	@Transactional
	void exec(C command);
}
