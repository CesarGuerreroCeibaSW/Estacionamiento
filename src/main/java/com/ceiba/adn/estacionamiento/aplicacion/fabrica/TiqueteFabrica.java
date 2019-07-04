package com.ceiba.adn.estacionamiento.aplicacion.fabrica;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;

@Component
public class TiqueteFabrica {

	public Tiquete create(TiqueteComando tiqueteComando) {
	    return new Tiquete(tiqueteComando.getPlaca(),
	    		tiqueteComando.isCilindrajeMayor500(),
	    		tiqueteComando.getTipoVehiculo(),
	    		Calendar.getInstance().getTime()
	    );
	}
}
