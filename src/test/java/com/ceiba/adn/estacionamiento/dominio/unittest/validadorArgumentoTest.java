package com.ceiba.adn.estacionamiento.dominio.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ceiba.adn.estacionamiento.dominio.entidad.validadorArgumento;
import com.ceiba.adn.estacionamiento.dominio.excepcion.ArgumentoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.TipoVehiculoInvalidoExcepcion;

public class validadorArgumentoTest {

	private static final String VACIO = "";
	private static final String BUS = "BUS";
	private static final String PLACA_VACIA = "Debe ingresar la placa";
	private static final String TIPO_VEHICULO_INVALIDO = "Debe ingresar un tipo valido de vehiculo";
	private static final String FECHA_VACIA = "Fecha vacia";
	
	@Test
	public void validadorArgumentoVacio() {
		try {
			// act
			validadorArgumento.validarRequerido(VACIO, PLACA_VACIA);
			fail();
		} catch (ArgumentoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), PLACA_VACIA);
		}
	}
	
	@Test
	public void argumentValidatorIsNull() {
		try {
			// act
			validadorArgumento.validarRequerido(null, PLACA_VACIA);
			fail();
		} catch (ArgumentoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), PLACA_VACIA);
		}
	}
	
	@Test
	public void argumentValidatorDateIsNull() {
		try {
			// act
			validadorArgumento.validarFechaRequerida(null,FECHA_VACIA);
			fail();
		} catch (ArgumentoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), FECHA_VACIA);
		}
	}
	
	@Test
	public void argumentValidatorInvalidTypeVehicle() {
		try {
			// act
			validadorArgumento.validarTipoVehiculo(BUS,TIPO_VEHICULO_INVALIDO);
			fail();
		} catch (TipoVehiculoInvalidoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), TIPO_VEHICULO_INVALIDO);
		}
	}

}
