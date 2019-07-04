package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class CapacidadMaximaMotoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 2L;

	public CapacidadMaximaMotoExcepcion(String msg) {
		super(msg);
	}
}
