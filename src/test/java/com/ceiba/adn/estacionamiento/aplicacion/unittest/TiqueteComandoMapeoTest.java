package com.ceiba.adn.estacionamiento.aplicacion.unittest;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;
import com.ceiba.adn.estacionamiento.aplicacion.mapeo.TiqueteComandoMapeo;

public class TiqueteComandoMapeoTest {

	
	@Test
	public void toCommandNull() {
		// act
		TiqueteComando dominio  = TiqueteComandoMapeo.getInstance().toCommand(null);
		// assert
		assertNull(dominio);
		
	}
}
