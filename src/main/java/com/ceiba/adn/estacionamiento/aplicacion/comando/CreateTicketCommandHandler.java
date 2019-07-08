package com.ceiba.adn.estacionamiento.aplicacion.comando;

import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.aplicacion.comun.CommandResponse;
import com.ceiba.adn.estacionamiento.aplicacion.comun.commandhandleresponse.CommandHandleResponse;
import com.ceiba.adn.estacionamiento.aplicacion.fabrica.TiqueteFabrica;
import com.ceiba.adn.estacionamiento.aplicacion.mapeo.TiqueteComandoMapeo;
import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.servicio.CrearTiqueteServicio;

@Component
public class CreateTicketCommandHandler implements CommandHandleResponse<TiqueteComando, CommandResponse<TiqueteComando>> {

	private final TiqueteFabrica fabrica;
	private final CrearTiqueteServicio crearTiqueteServicio;
	private static final TiqueteComandoMapeo mapper = TiqueteComandoMapeo.getInstance();

	public CreateTicketCommandHandler(TiqueteFabrica fabrica, CrearTiqueteServicio crearTiqueteServicio) {
		this.fabrica = fabrica;
		this.crearTiqueteServicio = crearTiqueteServicio;
	}

	public CommandResponse<TiqueteComando> exec(TiqueteComando command) {
		Tiquete tiquete = this.fabrica.create(command);
		return new CommandResponse<>(mapper.toCommand(this.crearTiqueteServicio.registrarEntrada(tiquete)));
	}

}
