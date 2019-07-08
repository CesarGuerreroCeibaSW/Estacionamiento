package com.ceiba.adn.estacionamiento.dominio.entidad;

import com.ceiba.adn.estacionamiento.dominio.excepcion.ArgumentoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.TipoVehiculoInvalidoExcepcion;

public final class ValidadorArgumento {
	
	private static final String VACIO = "";
	private static final String MOTO = "MOTO";
	private static final String CARRO = "CARRO";

	private ValidadorArgumento() {}
	
	
	public static void validarRequerido(Object valor,String mensaje) {
		if(valor == null || valor.equals(VACIO)) {
			throw new ArgumentoExcepcion(mensaje);
		}
	}
	
	public static void validarFechaRequerida(Object valor,String mensaje) {
		if(valor == null) {
			throw new ArgumentoExcepcion(mensaje);
		}
	}
	
	public static void validarTipoVehiculo(Object valor,String mensaje) {
		if(!valor.equals(MOTO) && !valor.equals(CARRO)) {
			throw new TipoVehiculoInvalidoExcepcion(mensaje);
		}
	}
}