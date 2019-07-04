package com.ceiba.adn.estacionamiento.infraestructura.persistencia.Repositorio.ImpleJpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.*;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.Entidad.EntidadTiquete;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.Mapeo.TiqueteMapeador;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.Repositorio.Query.QueryJPA;

@Component
public class CrearImpleJPA implements EstacionamientoCrearRepositorio {

	private static final TiqueteMapeador mapper = TiqueteMapeador.getInstance();
	
	@Autowired
	private QueryJPA jpa;

	@Override
	public Tiquete registrarEntrada(Tiquete tiquete) {
		EntidadTiquete entidad = mapper.toEntity(tiquete);
		return mapper.toDomain(jpa.save(entidad));
	}

}
