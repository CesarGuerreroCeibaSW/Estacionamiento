package com.ceiba.adn.estacionamiento.infraestructura.persistencia.Repositorio.impleJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.*;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.Repositorio.query.QueryJPA;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.entidad.EntidadTiquete;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.mapeo.TiqueteMapeador;

@Component
public class ActualizarImpleJPA implements EstacionamientoActualizarRepositorio {

	private static final TiqueteMapeador mapper = TiqueteMapeador.getInstance();
	
	@Autowired
	private QueryJPA jpa;

	@Override
	public boolean registrarSalida(Tiquete tiquete) {
		EntidadTiquete entidad = mapper.toEntity(tiquete);
		return jpa.save(entidad) != null;
	}

}
