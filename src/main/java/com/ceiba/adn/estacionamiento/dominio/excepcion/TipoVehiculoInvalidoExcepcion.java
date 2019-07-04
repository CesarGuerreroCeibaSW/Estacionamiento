package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class TipoVehiculoInvalidoExcepcion extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TipoVehiculoInvalidoExcepcion(String msg) {
		super(msg);
	}

}
