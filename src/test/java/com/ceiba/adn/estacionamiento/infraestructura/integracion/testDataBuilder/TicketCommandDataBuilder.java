package com.ceiba.adn.estacionamiento.infraestructura.integracion.testDataBuilder;

import com.ceiba.adn.estacionamiento.aplicacion.comando.TiqueteComando;

public class TicketCommandDataBuilder {

	private String placa;
	private boolean cilindrajeMayor500;
	private String tipoVehiculo;

	public TicketCommandDataBuilder() {
	}

	public TicketCommandDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TicketCommandDataBuilder conCilindraje(boolean cilindraje) {
		this.cilindrajeMayor500 = cilindraje;
		return this;
	}

	public TicketCommandDataBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public TiqueteComando build() {
		TiqueteComando tiquete = new TiqueteComando();
		tiquete.setCilindrajeMayor500(this.cilindrajeMayor500);
		tiquete.setPlaca(this.placa);
		tiquete.setTipoVehiculo(this.tipoVehiculo);
		return tiquete;
	}
}
