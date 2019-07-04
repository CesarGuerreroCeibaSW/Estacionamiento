package com.ceiba.adn.estacionamiento.dominio.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoActualizarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.servicio.ActualizarTiquetesServicio;
import com.ceiba.adn.estacionamiento.dominio.testdatabuilder.TiqueteTestDatabuilder;


public class ActualizarTiqueteServicioTest {
	
	
	private EstacionamientoActualizarRepositorio actualizarRepositorio;
	private EstacionamientoConsultarRepositorio consultarRepositorio;
	private ActualizarTiquetesServicio servicio;
	private TiqueteTestDatabuilder tiqueteBuilder;
	private Tiquete tiquete;
	private static final String MOTO = "MOTO";
	private static final String CARRO = "CARRO";
	private static final String PLACA = "HTP-123";

	private static final int DIA_PRUEBA_CARRO = 1;
	private static final int HORA_PRUEBA_CARRO = 2;
	private static final int DIA_PRUEBA_MOTO = 0;
	private static final int HORA_PRUEBA_MOTO = 10;
	private static final float PRECIO_PRUEBA_MOTO = 4000;
	private static final float PRECIO_PRUEBA_MOTO_CILINDRAJE_MAYOR = 6000;
	private static final float PRECIO_PRUEBA_CARRO = 11000;
	private static final float PRECIO_INGRESO_SALIDA_RAPIDA = 1000;

	@Before
	public void setUp() {
		// arrange
		this.consultarRepositorio = mock(EstacionamientoConsultarRepositorio.class);
		this.actualizarRepositorio = mock(EstacionamientoActualizarRepositorio.class);
	}

	@Test
	public void build() {
		// act
		this.servicio = new ActualizarTiquetesServicio(this.actualizarRepositorio,this.consultarRepositorio);
		// assert
		assertNotNull(this.actualizarRepositorio);
		assertNotNull(this.consultarRepositorio);
		assertNotNull(this.servicio);
	}

	@Test
	public void registrarSalidaCarro() {
		// arrange
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(ahoraCal.get(Calendar.YEAR), ahoraCal.get(Calendar.MONTH),
				ahoraCal.get(Calendar.DATE) - DIA_PRUEBA_CARRO, ahoraCal.get(Calendar.HOUR_OF_DAY) - HORA_PRUEBA_CARRO, 0);
		Date fechaIngreso = ahoraCal.getTime();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conTipoVehiculo(CARRO)
				.conFechaEntrada(fechaIngreso);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(this.tiquete);
		when(this.actualizarRepositorio.registrarSalida(this.tiquete)).thenReturn(true);
		this.servicio = new ActualizarTiquetesServicio(this.actualizarRepositorio, this.consultarRepositorio);
		// act
		Float price = this.servicio.registrarSalida(PLACA);
		boolean salida = this.actualizarRepositorio.registrarSalida(this.tiquete);
		// assert
		assertEquals(PRECIO_PRUEBA_CARRO, price, 0);
		assertEquals(true, salida);
	}
	
	@Test
	public void registrarSalidaMotoCilindrajeMayor() {
		// arrange
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(ahoraCal.get(Calendar.YEAR), ahoraCal.get(Calendar.MONTH),
				ahoraCal.get(Calendar.DATE) - DIA_PRUEBA_MOTO, ahoraCal.get(Calendar.HOUR_OF_DAY) - HORA_PRUEBA_MOTO, 0);
		Date fechaIngreso = ahoraCal.getTime();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conCilindraje(true)
				.conTipoVehiculo(MOTO).conFechaEntrada(fechaIngreso);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(this.tiquete);
		when(this.actualizarRepositorio.registrarSalida(this.tiquete)).thenReturn(true);
		this.servicio = new ActualizarTiquetesServicio(this.actualizarRepositorio, this.consultarRepositorio);
		// act
		Float price = this.servicio.registrarSalida(PLACA);
		boolean salida = this.actualizarRepositorio.registrarSalida(this.tiquete);
		// assert
		assertEquals(PRECIO_PRUEBA_MOTO_CILINDRAJE_MAYOR, price, 0);
		assertEquals(true, salida);
	}
	
	@Test
	public void registrarSalidaMoto() {
		// arrange
		Calendar ahoraCal = Calendar.getInstance();
		ahoraCal.set(ahoraCal.get(Calendar.YEAR), ahoraCal.get(Calendar.MONTH),
				ahoraCal.get(Calendar.DATE) - DIA_PRUEBA_MOTO, ahoraCal.get(Calendar.HOUR_OF_DAY) - HORA_PRUEBA_MOTO, 0);
		Date fechaIngreso = ahoraCal.getTime();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conTipoVehiculo(MOTO)
				.conFechaEntrada(fechaIngreso);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(this.tiquete);
		when(this.actualizarRepositorio.registrarSalida(this.tiquete)).thenReturn(true);
		this.servicio = new ActualizarTiquetesServicio(this.actualizarRepositorio, this.consultarRepositorio);
		// act
		Float price = this.servicio.registrarSalida(PLACA);
		boolean salida = this.actualizarRepositorio.registrarSalida(this.tiquete);
		// assert
		assertEquals(PRECIO_PRUEBA_MOTO, price, 0);
		assertEquals(true, salida);
	}

	@Test
	public void registrarSalidaHorasSobrantes() {
		// arrange
		Date fechaIngreso = Calendar.getInstance().getTime();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conTipoVehiculo(CARRO)
				.conFechaEntrada(fechaIngreso);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(this.tiquete);
		when(this.actualizarRepositorio.registrarSalida(this.tiquete)).thenReturn(true);
		this.servicio = new ActualizarTiquetesServicio(this.actualizarRepositorio, this.consultarRepositorio);
		// act
		Float price = this.servicio.registrarSalida(PLACA);
		boolean salida = this.actualizarRepositorio.registrarSalida(this.tiquete);
		// assert
		assertEquals(PRECIO_INGRESO_SALIDA_RAPIDA, price, 0);
		assertEquals(true, salida);
	}
	
}