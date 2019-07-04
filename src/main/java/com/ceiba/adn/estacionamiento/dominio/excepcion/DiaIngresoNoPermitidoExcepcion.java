package com.ceiba.adn.estacionamiento.dominio.excepcion;

public class DiaIngresoNoPermitidoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 54671L;

	public DiaIngresoNoPermitidoExcepcion(String msg) {
		super(msg);
	}
}

