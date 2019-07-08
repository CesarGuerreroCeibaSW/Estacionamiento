package com.ceiba.adn.estacionamiento.dominio.entidad;

import java.util.Date;

public class Tiquete {

	private Long id;
	private String placa;
	private Date fechaEntrada;
	private Date fechaSalida;
	private float precio;
	private String tipoVehiculo;
	private boolean cilindrajeMayor500;
	private boolean estadoIngreso;
	
	
	private static final String PLACA_VACIA = "Debe ingresar la placa";
	private static final String TIPO_VEHICULO_VACIO = "Debe ingresar el tipo de vehiculo";
	private static final String FECHA_ACTUAL_VACIA = "Debe registrar la fecha actual";
	private static final String TIPO_VEHICULO_INVALIDO = "Debe ingresar un tipo valido de vehiculo";
	private static final String CILINDRAJE_MOTO_VACIO = "Debe ingresar el cilindraje para las motos";
	private static final String MOTO  = "MOTO";
	
	public Tiquete() {
	}
	
	public Tiquete(String placa,boolean cilindraje,String tipoVehiculo,Date fechaEntrada) {
		
		ValidadorArgumento.validarRequerido(placa, PLACA_VACIA);
		ValidadorArgumento.validarRequerido(tipoVehiculo, TIPO_VEHICULO_VACIO);
		ValidadorArgumento.validarTipoVehiculo(tipoVehiculo, TIPO_VEHICULO_INVALIDO);
		ValidadorArgumento.validarFechaRequerida(fechaEntrada, FECHA_ACTUAL_VACIA);
		
		this.placa= placa;
		this.fechaEntrada = fechaEntrada;
		this.estadoIngreso = true;
		this.tipoVehiculo = tipoVehiculo;
		
		if(tipoVehiculo.equalsIgnoreCase(MOTO)) {
			ValidadorArgumento.validarRequerido(cilindraje, CILINDRAJE_MOTO_VACIO);
			this.cilindrajeMayor500 = cilindraje;
		}
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public boolean isCilindrajeMayor500() {
		return cilindrajeMayor500;
	}

	public void setCilindrajeMayor500(boolean cilindrajeMayor500) {
		this.cilindrajeMayor500 = cilindrajeMayor500;
	}

	public boolean isEstadoIngreso() {
		return estadoIngreso;
	}
	public void setEstadoIngreso(boolean estadoIngreso) {
		this.estadoIngreso = estadoIngreso;
	}
	
	
	
}
