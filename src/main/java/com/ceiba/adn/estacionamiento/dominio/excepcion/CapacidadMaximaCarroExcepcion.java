package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class CapacidadMaximaCarroExcepcion extends RuntimeException {

	private static final long serialVersionUID = 3L;

	public CapacidadMaximaCarroExcepcion(String msg) {
		super(msg);
	}
}
