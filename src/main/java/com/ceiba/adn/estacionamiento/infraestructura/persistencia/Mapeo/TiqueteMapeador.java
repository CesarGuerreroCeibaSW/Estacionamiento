package com.ceiba.adn.estacionamiento.infraestructura.persistencia.Mapeo;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.infraestructura.persistencia.Entidad.EntidadTiquete;

public final class TiqueteMapeador {

	private TiqueteMapeador() {
	}

	private static final TiqueteMapeador INSTANCE = new TiqueteMapeador();

	public static TiqueteMapeador getInstance() {
		return INSTANCE;
	}

	public Tiquete toDomain(EntidadTiquete entidad) {
		if(entidad == null) {
			return null;
		}
		Tiquete dominio = new Tiquete();
		dominio.setCilindrajeMayor500(entidad.isCilindrajeMayor500());
		dominio.setFechaEntrada(entidad.getFechaEntrada());
		dominio.setFechaSalida(entidad.getFechaSalida());
		dominio.setId(entidad.getId());
		dominio.setPlaca(entidad.getPlaca());
		dominio.setPrecio(entidad.getPrecio());
		dominio.setEstadoIngreso(entidad.isEstadoIngreso());
		dominio.setTipoVehiculo(entidad.getTipoVehiculo());
		return dominio;
	}

	public EntidadTiquete toEntity(Tiquete dominio) {
		if(dominio == null) {
			return null;
		}
		EntidadTiquete entidad = new EntidadTiquete();
		entidad.setCilindrajeMayor500(dominio.isCilindrajeMayor500());
		entidad.setFechaEntrada(dominio.getFechaEntrada());
		entidad.setFechaSalida(dominio.getFechaSalida());
		entidad.setId(dominio.getId());
		entidad.setPlaca(dominio.getPlaca());
		entidad.setPrecio(dominio.getPrecio());
		entidad.setEstadoIngreso(dominio.isEstadoIngreso());
		entidad.setTipoVehiculo(dominio.getTipoVehiculo());
		return entidad;
	}

}
