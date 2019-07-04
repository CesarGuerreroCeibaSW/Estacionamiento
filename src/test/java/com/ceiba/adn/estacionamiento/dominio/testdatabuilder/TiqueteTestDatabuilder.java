package com.ceiba.adn.estacionamiento.dominio.testdatabuilder;

import java.util.Date;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;

public class TiqueteTestDatabuilder {

	private Long id;
	private String placa;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float precio;
	private boolean estadoIngreso;
	private boolean cilindrajeMayor500;
	private String tipoVehiculo;

	public TiqueteTestDatabuilder() {
	}

	public TiqueteTestDatabuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public TiqueteTestDatabuilder conFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}

	public TiqueteTestDatabuilder conPrecio(float precio) {
		this.precio = precio;
		return this;
	}

	public TiqueteTestDatabuilder conEstadoIngreso(boolean estado) {
		this.estadoIngreso = estado;
		return this;
	}

	public TiqueteTestDatabuilder conCilindraje(boolean cilindraje) {
		this.cilindrajeMayor500 = cilindraje;
		return this;
	}

	public TiqueteTestDatabuilder conTipoVehiculo(String tipo) {
		this.tipoVehiculo = tipo;
		return this;
	}
	
	public TiqueteTestDatabuilder tiqueteMoto(String placa, String tipoVehiculo, boolean cilindrajeMayor500, Date fechaIngreso) {
		this.placa = placa;
		this.tipoVehiculo= tipoVehiculo;
		this.cilindrajeMayor500 = cilindrajeMayor500;
		this.fechaEntrada = fechaIngreso;
		this.estadoIngreso = true;
		
		return this;
	}
	
	public TiqueteTestDatabuilder tiqueteCarro(String placa, String tipoVehiculo, Date fechaIngreso) {
		this.placa = placa;
		this.tipoVehiculo= tipoVehiculo;
		this.fechaEntrada = fechaIngreso;
		this.estadoIngreso = true;
		
		return this;
	}

	public Tiquete build() {
		Tiquete tiquete = new Tiquete();
		tiquete.setId(this.id);
		tiquete.setCilindrajeMayor500(this.cilindrajeMayor500);
		tiquete.setFechaEntrada(this.fechaEntrada);
		tiquete.setFechaSalida(this.fechaSalida);
		tiquete.setPlaca(this.placa);
		tiquete.setPrecio(this.precio);
		tiquete.setEstadoIngreso(this.estadoIngreso);
		tiquete.setTipoVehiculo(this.tipoVehiculo);
		return tiquete;
	}
	
	public Tiquete buildWithParams(String placa, boolean cilindraje, String tipoVehiculo, Date fechaEntrada) {
		Tiquete tiquete = new Tiquete(placa, cilindraje, tipoVehiculo, fechaEntrada);
		tiquete.setId(this.id);
		tiquete.setFechaSalida(this.fechaSalida);
		tiquete.setPrecio(this.precio);
		tiquete.setEstadoIngreso(this.estadoIngreso);
		return tiquete;
	}
}
