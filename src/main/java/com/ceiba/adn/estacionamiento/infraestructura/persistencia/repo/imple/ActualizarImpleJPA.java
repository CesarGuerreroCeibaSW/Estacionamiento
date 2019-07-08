package com.ceiba.adn.estacionamiento.infraestructura.persistencia.repo.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.*;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.entidad.EntidadTiquete;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.mapeotiquete.TiqueteMapeador;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.repo.query.QueryJPA;

@Component
public class ActualizarImpleJPA implements EstacionamientoActualizarRepositorio {

	private static final TiqueteMapeador mapper = TiqueteMapeador.getInstance();
	
	@Autowired
	private QueryJPA jpa;

	@Override
	public boolean registrarSalida(Tiquete tiquete) {
		EntidadTiquete entidad = mapper.toEntity(tiquete);
		jpa.save(entidad);
		return true;
	}

}
