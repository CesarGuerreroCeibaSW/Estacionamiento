package com.ceiba.adn.estacionamiento.aplicacion.query;

import java.util.List;
import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.dominio.servicio.BuscarTiquetesActivosServicio;

@Component
public class BuscarTiquetesActivosQueryManejador{

	private final BuscarTiquetesActivosServicio buscarTiquetesActivosServicio;

	public BuscarTiquetesActivosQueryManejador(BuscarTiquetesActivosServicio buscarTiquetesActivosServicio) {
		this.buscarTiquetesActivosServicio = buscarTiquetesActivosServicio;
	}
	
	public List<TiqueteActivoQuery> exec(){
		return this.buscarTiquetesActivosServicio.buscarTiquetesActivos();
	}
}
