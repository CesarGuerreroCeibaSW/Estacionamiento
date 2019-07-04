package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class VehiculoNoEncontradoExcepcion extends RuntimeException {
	
	private static final long serialVersionUID = 542323671L;

	public VehiculoNoEncontradoExcepcion(String msg) {
		super(msg);
	}
}
