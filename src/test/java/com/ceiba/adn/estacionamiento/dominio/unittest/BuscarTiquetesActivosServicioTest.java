package com.ceiba.adn.estacionamiento.dominio.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.excepcion.DiaIngresoNoPermitidoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.VehiculoNoEncontradoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.servicio.BuscarTiquetesActivosServicio;
import com.ceiba.adn.estacionamiento.dominio.testdatabuilder.TiqueteTestDatabuilder;

public class BuscarTiquetesActivosServicioTest {

	private EstacionamientoConsultarRepositorio consultarRepositorio;
	private BuscarTiquetesActivosServicio servicio;
	private Date fechaActual;
	private static final String CARRO = "CARRO";
	private static final String PLACA = "HTP-123";
	private static final String VEHICULO_NO_ENCONTRADO = "El vehiculo no ha sido encontrado";

	@Before
	public void setUp() {
		// arrange
		this.consultarRepositorio = mock(EstacionamientoConsultarRepositorio.class);
	}

	@Test
	public void build() {
		// act
		this.servicio = new BuscarTiquetesActivosServicio(this.consultarRepositorio);
		// assert
		assertNotNull(this.consultarRepositorio);
		assertNotNull(this.servicio);
	}

	@Test
	 public void buscarTiquetesActivos() {
		// arrange
		List<TiqueteActivoQuery> listaRespuesta = new ArrayList<TiqueteActivoQuery>();
		this.servicio = new BuscarTiquetesActivosServicio(this.consultarRepositorio);
		// act
		List<TiqueteActivoQuery> respuesta = this.servicio.buscarTiquetesActivos();
		// assert
		assertEquals(respuesta, listaRespuesta);
	}
	
	@Test
	 public void buscarTiqueteActivo() {
		// arrange
		TiqueteTestDatabuilder tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA)
				.conTipoVehiculo(CARRO).conFechaEntrada(fechaActual);
		tiqueteBuilder = new TiqueteTestDatabuilder().conPlaca(PLACA)
				.conTipoVehiculo(CARRO).conFechaEntrada(fechaActual).conPrecio(0).conEstadoIngreso(true);
		Tiquete tiqueteRegistrado = tiqueteBuilder.build();
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(tiqueteRegistrado);
		this.servicio = new BuscarTiquetesActivosServicio(this.consultarRepositorio);
		// act
		Tiquete respuesta = this.servicio.buscarTiqueteActivo(PLACA);
		// assert
		assertEquals(respuesta, tiqueteRegistrado);
	}
	
	@Test
	 public void buscarTiqueteActivoNul() {
		// arrange
		when(this.consultarRepositorio.buscarVehiculo(PLACA)).thenReturn(null);
		this.servicio = new BuscarTiquetesActivosServicio(this.consultarRepositorio);
		
		try {
			// act
			this.servicio.buscarTiqueteActivo(PLACA);
			fail();
		} catch (VehiculoNoEncontradoExcepcion e) {
			// assert
			assertEquals(e.getMessage(), VEHICULO_NO_ENCONTRADO);
		}
	}
	
}
