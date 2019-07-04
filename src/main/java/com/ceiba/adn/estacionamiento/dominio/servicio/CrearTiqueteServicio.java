package com.ceiba.adn.estacionamiento.dominio.servicio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.*;
import com.ceiba.adn.estacionamiento.dominio.excepcion.*;

public class CrearTiqueteServicio {

	private EstacionamientoCrearRepositorio crearRepositorio;
	private EstacionamientoConsultarRepositorio consultarRepositorio;
	
	private static final String VEHICULO_REGISTRADO = "El vehiculo ya se encuentra registrado";
	private static final String ESTACIONAMIENTO_CARRO_LLENO = "No hay espacio disponible para ingresar el carro";
	private static final String ESTACIONAMIENTO_MOTO_LLENO = "No hay espacio disponible para ingresar la moto";
	private static final String DIA_INGRESO_NO_PERMITIDO = "El vehiculo no esta autorizado a ingresar los dias Domingo y Lunes";
	
	public CrearTiqueteServicio(EstacionamientoCrearRepositorio crearRepositorio,
			EstacionamientoConsultarRepositorio consultarRepositorio) {
		this.crearRepositorio = crearRepositorio;
		this.consultarRepositorio = consultarRepositorio;
		
	}

	public Tiquete registrarEntrada(Tiquete tiquete) {
		this.validarRegistro(tiquete.getPlaca());
		this.validarCapacidadTipoVehiculo(tiquete.getTipoVehiculo());
		this.validarFechaEntradaVehiculo(tiquete.getPlaca(), tiquete.getFechaEntrada());
		return this.crearRepositorio.registrarEntrada(tiquete); 
	}
	
	private void validarRegistro(String placa) {
		Tiquete encontrado = this.consultarRepositorio.buscarVehiculo(placa); 
		if(encontrado != null) {
			throw new VehiculoRegistradoExcepcion(VEHICULO_REGISTRADO);
		}
	}

	private void validarCapacidadTipoVehiculo(String tipoVehiculo) {
		if(tipoVehiculo.equalsIgnoreCase("CARRO") && this.consultarRepositorio.contarCarrosActivos() > 19) {
			throw new CapacidadMaximaCarroExcepcion(ESTACIONAMIENTO_CARRO_LLENO);
		} else if (tipoVehiculo.equalsIgnoreCase("MOTO") && this.consultarRepositorio.contarMotosActivas() > 9){
			throw new CapacidadMaximaMotoExcepcion(ESTACIONAMIENTO_MOTO_LLENO);
		} 
	}

	private void validarFechaEntradaVehiculo(String placa,Date fecha) {
		
		char primeraLetra = placa.charAt(0);
		if(primeraLetra == 'A') {
			GregorianCalendar calendario = new GregorianCalendar();
			calendario.setTime(fecha);
			if(calendario.get(Calendar.DAY_OF_WEEK) < 3) {
				throw new DiaIngresoNoPermitidoExcepcion(DIA_INGRESO_NO_PERMITIDO);
			}
		}
		
	}
}
