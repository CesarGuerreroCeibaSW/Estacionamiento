package com.ceiba.adn.estacionamiento.dominio.servicio;

import java.util.List;

import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.excepcion.VehiculoNoEncontradoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;;


public class BuscarTiquetesActivosServicio {

	
	private EstacionamientoConsultarRepositorio consultarRepositorio;
	private static final String VEHICULO_NO_ENCONTRADO = "El vehiculo no ha sido encontrado";
	
	public BuscarTiquetesActivosServicio(EstacionamientoConsultarRepositorio consultarRepositorio) {
		this.consultarRepositorio = consultarRepositorio;
	}
	
	public List<TiqueteActivoQuery> buscarTiquetesActivos(){
	  return this.consultarRepositorio.listarVehiculos();
	}
	
	public Tiquete buscarTiqueteActivo(String placa) {
		Tiquete tiquete = this.consultarRepositorio.buscarVehiculo(placa);
		if(tiquete == null) {
			throw new VehiculoNoEncontradoExcepcion(VEHICULO_NO_ENCONTRADO);
		}
		return tiquete;
	}

}
