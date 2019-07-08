package com.ceiba.adn.estacionamiento.dominio.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoCrearRepositorio;
import com.ceiba.adn.estacionamiento.dominio.servicio.CrearTiqueteServicio;
import com.ceiba.adn.estacionamiento.dominio.testdatabuilder.TiqueteTestDatabuilder;
import com.ceiba.adn.estacionamiento.dominio.excepcion.CapacidadMaximaCarroExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.CapacidadMaximaMotoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.DiaIngresoNoPermitidoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.VehiculoRegistradoExcepcion;

public class CrearTiqueteServicioTest {

	private EstacionamientoCrearRepositorio crearRepositorio;
	private EstacionamientoConsultarRepositorio consultarRepositorio;
	private CrearTiqueteServicio servicio;
	private TiqueteTestDatabuilder tiqueteBuilder;
	private Tiquete tiquete;
	private Date fechaActual;
	private Date domingo;
	private Date martes;
	private static final String MOTO = "MOTO";
	private static final String CARRO = "CARRO";
	private static final boolean CILINDRAJE_MAYOR = true;
	private static final String PLACA = "HTP-123";
	private static final String PLACA_A = "AAA-000";
	private static final String DIA_INGRESO_NO_PERMITIDO = "El vehiculo no esta autorizado a ingresar los dias Domingo y Lunes";
	private static final String VEHICULO_REGISTRADO = "El vehiculo ya se encuentra registrado";
	private static final String ESTACIONAMIENTO_CARRO_LLENO = "No hay espacio disponible para ingresar el carro";
	private static final String ESTACIONAMIENTO_MOTO_LLENO = "No hay espacio disponible para ingresar la moto";
	private static final long CAPACIDAD_MAXIMA_MOTO = 10;
	private static final long CAPACIDAD_MAXIMA_CARRO = 20;

	@Before
	public void setUp() {
		// arrange
		this.consultarRepositorio = mock(EstacionamientoConsultarRepositorio.class);
		this.crearRepositorio = mock(EstacionamientoCrearRepositorio.class);
		this.fechaActual = Calendar.getInstance().getTime();
		this.domingo = Calendar.getInstance().getTime();
		this.domingo.setMonth(5);
		this.domingo.setDate(30);
		this.domingo.setYear(2019);
		this.martes = Calendar.getInstance().getTime();
		this.martes.setMonth(6);
		this.martes.setDate(2);
		this.martes.setYear(2019);
	}

	@Test
	public void build() {
		// act
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio,this.consultarRepositorio);
		// assert
		assertNotNull(this.crearRepositorio);
		assertNotNull(this.consultarRepositorio);
		assertNotNull(this.servicio);
	}

	@Test
	 public void crearTiqueteMoto() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conCilindraje(CILINDRAJE_MAYOR).conPlaca(PLACA)
				.conTipoVehiculo(MOTO).conFechaEntrada(fechaActual);
		this.tiquete = this.tiqueteBuilder.build();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conCilindraje(CILINDRAJE_MAYOR).conPlaca(PLACA)
				.conTipoVehiculo(MOTO).conFechaEntrada(fechaActual).conPrecio(0).conEstadoIngreso(true);
		Tiquete tiqueteRegistrado = this.tiqueteBuilder.build();
		when(this.crearRepositorio.registrarEntrada(this.tiquete)).thenReturn(tiqueteRegistrado);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, consultarRepositorio);
		// act
		Tiquete respuesta = this.servicio.registrarEntrada(this.tiquete);
		// assert
		assertEquals(respuesta, tiqueteRegistrado);
	}
	
	@Test
	 public void crearTiqueteCarro() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA)
				.conTipoVehiculo(CARRO).conFechaEntrada(fechaActual);
		this.tiquete = this.tiqueteBuilder.build();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA)
				.conTipoVehiculo(CARRO).conFechaEntrada(fechaActual).conPrecio(0).conEstadoIngreso(true);
		Tiquete tiqueteRegistrado = this.tiqueteBuilder.build();
		when(this.crearRepositorio.registrarEntrada(this.tiquete)).thenReturn(tiqueteRegistrado);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, consultarRepositorio);
		// act
		Tiquete respuesta = this.servicio.registrarEntrada(this.tiquete);
		// assert
		assertEquals(respuesta, tiqueteRegistrado);
	}
	
	@Test
	 public void crearTiquetePlacaConADomingo() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA_A)
				.conTipoVehiculo(CARRO).conFechaEntrada(domingo);
		this.tiquete = this.tiqueteBuilder.build();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA_A)
				.conTipoVehiculo(CARRO).conFechaEntrada(domingo).conPrecio(0).conEstadoIngreso(true);
		Tiquete tiqueteRegistrado = this.tiqueteBuilder.build();
		when(this.crearRepositorio.registrarEntrada(this.tiquete)).thenReturn(tiqueteRegistrado);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, consultarRepositorio);
		// assert
		try {
			// act
			this.servicio.registrarEntrada(this.tiquete);
			fail();
		} catch (DiaIngresoNoPermitidoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), DIA_INGRESO_NO_PERMITIDO);
		}
	}
	
	@Test
	 public void crearTiquetePlacaA() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA_A)
				.conTipoVehiculo(CARRO).conFechaEntrada(martes);
		this.tiquete = this.tiqueteBuilder.build();
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA_A)
				.conTipoVehiculo(CARRO).conFechaEntrada(martes).conPrecio(0).conEstadoIngreso(true);
		Tiquete tiqueteRegistrado = this.tiqueteBuilder.build();
		when(this.crearRepositorio.registrarEntrada(this.tiquete)).thenReturn(tiqueteRegistrado);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, consultarRepositorio);
		// act
		Tiquete respuesta = this.servicio.registrarEntrada(this.tiquete);
		// assert
		assertEquals(respuesta, tiqueteRegistrado);
	}
	
	@Test
	public void tiqueteRegistrado() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conTipoVehiculo(CARRO)
				.conFechaEntrada(fechaActual);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(this.tiquete);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, this.consultarRepositorio);
		try {
			// act
			this.servicio.registrarEntrada(this.tiquete);
		} catch (VehiculoRegistradoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), VEHICULO_REGISTRADO);
		}
	}
	
	@Test
	public void capacidadMotosMaxima() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conCilindraje(true)
				.conFechaEntrada(fechaActual).conTipoVehiculo(MOTO);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.contarMotosActivas()).thenReturn(CAPACIDAD_MAXIMA_MOTO);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, this.consultarRepositorio);
		try {
			// act
			this.servicio.registrarEntrada(this.tiquete);
			fail();
		} catch (CapacidadMaximaMotoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), ESTACIONAMIENTO_MOTO_LLENO);
		}
	}

	@Test
	public void validateFullParkingCars() {
		// arrange
		this.tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA).conFechaEntrada(fechaActual)
				.conTipoVehiculo(CARRO);
		this.tiquete = this.tiqueteBuilder.build();
		when(this.consultarRepositorio.contarCarrosActivos()).thenReturn(CAPACIDAD_MAXIMA_CARRO);
		this.servicio = new CrearTiqueteServicio(this.crearRepositorio, this.consultarRepositorio);
		try {
			// act
			this.servicio.registrarEntrada(this.tiquete);
			fail();
		} catch (CapacidadMaximaCarroExcepcion e) {
			// assert
			assertEquals(e.getMessage(), ESTACIONAMIENTO_CARRO_LLENO);
		}
	}
	
	
	
}
