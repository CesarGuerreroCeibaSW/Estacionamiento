package com.ceiba.adn.estacionamiento.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;

public interface EstacionamientoConsultarRepositorio {

	public List<TiqueteActivoQuery> listarVehiculos();
	
	public Tiquete buscarVehiculo(String placa);
	
	public long contarCarrosActivos();
	
	public long contarMotosActivas();
	
	public boolean validarExistente(String placa);
	
	
}
