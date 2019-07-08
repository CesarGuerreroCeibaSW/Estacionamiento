package com.ceiba.adn.estacionamiento.aplicacion.mapeo;

import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;

public final class TiqueteComandoMapeo {

	private TiqueteComandoMapeo() {
	}

	private static final TiqueteComandoMapeo INSTANCE = new TiqueteComandoMapeo();

	public static TiqueteComandoMapeo getInstance() {
		return INSTANCE;
	}

	public TiqueteComando toCommand(Tiquete entidad) {
		if(entidad == null) {
			return null;	
		}
		TiqueteComando dominio = new TiqueteComando();
		dominio.setCilindrajeMayor500(entidad.isCilindrajeMayor500());
		dominio.setPlaca(entidad.getPlaca());
		dominio.setTipoVehiculo(entidad.getTipoVehiculo());
		return dominio;
	}


}
