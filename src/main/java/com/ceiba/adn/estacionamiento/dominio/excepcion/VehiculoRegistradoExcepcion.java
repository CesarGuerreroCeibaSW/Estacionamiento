package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class VehiculoRegistradoExcepcion extends RuntimeException{
	private static final long serialVersionUID = 6598985L;

	public VehiculoRegistradoExcepcion(String msg) {
		super(msg);
	}
}
