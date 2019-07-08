package com.ceiba.adn.estacionamiento.infraestructura.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.adn.estacionamiento.aplicacion.comando.CreateTicketCommandHandler;
import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;
import com.ceiba.adn.estacionamiento.aplicacion.comando.UpdateTicketCommandHandler;
import com.ceiba.adn.estacionamiento.aplicacion.comun.CommandResponse;
import com.ceiba.adn.estacionamiento.aplicacion.query.BuscarTiquetesActivosQueryManejador;
import com.ceiba.adn.estacionamiento.aplicacion.query.TiqueteActivoQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/tiquete")
@Api(tags = { "Controlador tiquete"})
public class ControladorEstacionamiento {
	
	private final CreateTicketCommandHandler createTicketCommandHandler;
	private final UpdateTicketCommandHandler updateTicketCommandHandler;
	private final BuscarTiquetesActivosQueryManejador findActiveTicketsQueryHandler;
	
	
	public ControladorEstacionamiento(CreateTicketCommandHandler createTicketCommandHandler,
			UpdateTicketCommandHandler updateTicketCommandHandler,
			BuscarTiquetesActivosQueryManejador findActiveTicketsQueryHandler) {
		this.createTicketCommandHandler = createTicketCommandHandler;
		this.updateTicketCommandHandler = updateTicketCommandHandler;
		this.findActiveTicketsQueryHandler = findActiveTicketsQueryHandler;
	}
	
	@PostMapping
	@ApiOperation("Guardar tiquete")
	public CommandResponse<TiqueteComando> post(@RequestBody TiqueteComando command) {
		return createTicketCommandHandler.exec(command);
	}
	

	@GetMapping
	@ApiOperation("Consultar tiquete")
	public List<TiqueteActivoQuery> get() {
		return this.findActiveTicketsQueryHandler.exec();
	}
	
	@PutMapping()
	@ApiOperation("Actualizar tiquete")
	public CommandResponse<Float> put(@RequestBody TiqueteComando tiquete) {
		return this.updateTicketCommandHandler.exec(tiquete.getPlaca());
	}
	
}
