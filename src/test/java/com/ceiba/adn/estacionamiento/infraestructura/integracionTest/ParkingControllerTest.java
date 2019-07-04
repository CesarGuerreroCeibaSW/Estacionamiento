package com.ceiba.adn.estacionamiento.infraestructura.integracionTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.adn.estacionamiento.ApplicationMock;
import com.ceiba.adn.estacionamiento.EstacionamientoApplication;
import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;
import com.ceiba.adn.estacionamiento.infraestructura.integracion.testDataBuilder.TicketCommandDataBuilder;
import com.ceiba.adn.estacionamiento.aplicacion.comun.CommandResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@SpringBootTest(classes = EstacionamientoApplication.class)
@AutoConfigureMockMvc
public class ParkingControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	private static final String API_URL = "/api/tiquete/";
	private static final String CARRO = "CARRO";
	private static final String MOTO = "MOTO";
	private static final String PLACA_CARRO = "BCD-234";
	private static final String PLACA_MOTO = "MTO-233";
	private static final String PLACA_CILINDRAJE = "CLN-555";
	private static final float PRECIO_CARRO = (float) 1000.0;
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getActiveTickets() throws Exception {
		this.mvc.perform(get(API_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void registroCarro() throws Exception {
		TicketCommandDataBuilder ticketBuilder = new TicketCommandDataBuilder().conPlaca(PLACA_CARRO)
				.conTipoVehiculo(CARRO); 
		TiqueteComando ticket = ticketBuilder.build();
		JSONObject jsonTicketCommand = new JSONObject(ticket);
		CommandResponse<TiqueteComando> commandResponse = new CommandResponse<>(ticket);
		JSONObject jsonTicketCommanResponse = new JSONObject(commandResponse);
		mvc.perform(post(API_URL).content(jsonTicketCommand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(jsonTicketCommanResponse.toString()));
	}
	
	@Test
	public void registroMoto() throws Exception {
		TicketCommandDataBuilder ticketBuilder = new TicketCommandDataBuilder().conPlaca(PLACA_MOTO)
				.conTipoVehiculo(MOTO); 
		TiqueteComando ticket = ticketBuilder.build();
		JSONObject jsonTicketCommand = new JSONObject(ticket);
		CommandResponse<TiqueteComando> commandResponse = new CommandResponse<>(ticket);
		JSONObject jsonTicketCommanResponse = new JSONObject(commandResponse);
		mvc.perform(post(API_URL).content(jsonTicketCommand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(jsonTicketCommanResponse.toString()));
	}
	
	@Test
	public void registroCilindraje() throws Exception {
		TicketCommandDataBuilder ticketBuilder = new TicketCommandDataBuilder().conPlaca(PLACA_CILINDRAJE)
				.conTipoVehiculo(MOTO).conCilindraje(true);
		TiqueteComando ticket = ticketBuilder.build();
		JSONObject jsonTicketCommand = new JSONObject(ticket);
		CommandResponse<TiqueteComando> commandResponse = new CommandResponse<>(ticket);
		JSONObject jsonTicketCommanResponse = new JSONObject(commandResponse);
		mvc.perform(post(API_URL).content(jsonTicketCommand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(jsonTicketCommanResponse.toString()));
	}
		
	@Test
	public void registroSalidaCarro() throws Exception {
		this.registroCarro();
		TicketCommandDataBuilder ticketBuilder = new TicketCommandDataBuilder().conPlaca(PLACA_CARRO);
		TiqueteComando ticket = ticketBuilder.build();
		JSONObject jsonTicketCommand = new JSONObject(ticket);
		CommandResponse<Float> responseExit = new CommandResponse<>(PRECIO_CARRO);
		JSONObject jsonTicketCommanResponse = new JSONObject(responseExit);
		mvc.perform(put(API_URL).content(jsonTicketCommand.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect((content().json(jsonTicketCommanResponse.toString())));
	}
	
}
