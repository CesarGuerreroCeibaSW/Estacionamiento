package com.ceiba.adn.estacionamiento.dominio.unittest;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.testdatabuilder.TiqueteTestDatabuilder;

public class TiqueteTest {

	private TiqueteTestDatabuilder tiqueteBuilder;
	private Tiquete tiquete;
	
	private static final Date FECHA_EMTRADA = Calendar.getInstance().getTime();
	private static final String PLACA_MOTO = "UFP-201";
	private static final String PLACA_CARRO = "FPS-013";
	private static final boolean CILINDRAJE_MAYOR= true;
	private static final String CARRO = "CARRO";
	private static final String MOTO = "MOTO";

	@Test
	public void crearTiqueteCarro() {
		this.tiqueteBuilder = new TiqueteTestDatabuilder();
		// act
		this.tiquete = this.tiqueteBuilder.buildWithParams(PLACA_CARRO,false,CARRO,FECHA_EMTRADA);
		// assert
		assertNotNull(tiquete);
		
	}
	
	@Test
	public void crearTiqueteMoto() {
		this.tiqueteBuilder = new TiqueteTestDatabuilder();
		// act
		this.tiquete = this.tiqueteBuilder.buildWithParams(PLACA_MOTO,true,MOTO,FECHA_EMTRADA);
		// assert
		assertNotNull(tiquete);
		
	}

}
