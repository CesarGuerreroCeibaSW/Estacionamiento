package com.ceiba.adn.estacionamiento.infraestructura.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoActualizarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoCrearRepositorio;
import com.ceiba.adn.estacionamiento.dominio.servicio.ActualizarTiquetesServicio;
import com.ceiba.adn.estacionamiento.dominio.servicio.BuscarTiquetesActivosServicio;
import com.ceiba.adn.estacionamiento.dominio.servicio.CrearTiqueteServicio;


@Configuration
public class ServicioBean {

	@Bean
	public BuscarTiquetesActivosServicio servicioActualizarUsuario(EstacionamientoConsultarRepositorio consultarRepositorio)  {
		return  new BuscarTiquetesActivosServicio(consultarRepositorio);
	}
	
	@Bean
	public ActualizarTiquetesServicio servicioEliminarUsuario(EstacionamientoActualizarRepositorio actualizarRepositorio,
			EstacionamientoConsultarRepositorio consultarRepositorio)  {
		return  new ActualizarTiquetesServicio(actualizarRepositorio, consultarRepositorio);
	}
	
	@Bean
	public CrearTiqueteServicio servicioCrearUsuario(EstacionamientoCrearRepositorio crearRepositorio,
				EstacionamientoConsultarRepositorio consultarRepositorio) {
		return  new CrearTiqueteServicio(crearRepositorio, consultarRepositorio);
	}

	
}
