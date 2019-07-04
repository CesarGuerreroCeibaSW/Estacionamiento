package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class ArgumentoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArgumentoExcepcion(String msg) {
		super(msg);
	}
}

