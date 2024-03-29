package com.ceiba.adn.estacionamiento.infraestructura.persistencia.repo.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.*;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.entidadbd.EntidadTiquete;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.mapeo.TiqueteMapeador;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.repo.query.QueryJPA;

@Component
public class ConsultarImpleJPA implements EstacionamientoConsultarRepositorio {

	private static final TiqueteMapeador mapper = TiqueteMapeador.getInstance();
	
	@Autowired
	private QueryJPA jpa;


	@Override
	public List<TiqueteActivoQuery> listarVehiculos() {
		return jpa.tiquetesActivos();
	}

	@Override
	public Tiquete buscarVehiculo(String placa) {
		EntidadTiquete entity = jpa.buscarPorPlaca(placa);
		return mapper.toDomain(entity);
	}

	@Override
	public long contarCarrosActivos() {
		return jpa.contarCarrosActivos();
	}

	@Override
	public long contarMotosActivas() {
		return jpa.contarMotosActivas();
	}

}
