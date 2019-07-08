package com.ceiba.adn.estacionamiento.aplicacion.comando;

import org.springframework.stereotype.Component;

import com.ceiba.adn.estacionamiento.aplicacion.comun.CommandResponse;
import com.ceiba.adn.estacionamiento.aplicacion.comun.commandhandleresponse.CommandHandleResponse;
import com.ceiba.adn.estacionamiento.dominio.servicio.ActualizarTiquetesServicio;

@Component
public class UpdateTicketCommandHandler implements CommandHandleResponse<String, CommandResponse<Float>> {

	private final ActualizarTiquetesServicio actualizarTiquetesServicio;

	public UpdateTicketCommandHandler(ActualizarTiquetesServicio updateTicketService) {
		this.actualizarTiquetesServicio = updateTicketService;
	}

	@Override
	public CommandResponse<Float> exec(String placa) {
		
		Float price = this.actualizarTiquetesServicio.registrarSalida(placa);
		return new CommandResponse<>(price);
	}

}
