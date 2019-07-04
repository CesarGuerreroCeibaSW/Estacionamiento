package com.ceiba.adn.estacionamiento.aplicacion.query;

import java.util.Date;

public class TiqueteActivoQuery {
	
	private String placa;
	private String fechaEntrada;
	private String tipoVehiculo;
	
	public TiqueteActivoQuery(String placa,Date fechaEntrada,String tipoVehiculo) {
		this.fechaEntrada=  String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM", fechaEntrada);
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}


}
