package com.ceiba.adn.estacionamiento.infraestructura.persistencia.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tiquete")
public class EntidadTiquete implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "placa", nullable = false)
	private String placa;
	
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaEntrada", nullable = false)
	private Date fechaEntrada;
	
	@javax.persistence.Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaSalida", nullable = true)
	private Date fechaSalida;
	
	@Column(name = "precio", nullable = true)
	private float precio;
	
	@Column(name = "estadoIngreso", nullable = false)
	private boolean estadoIngreso;
	
	@Column(name = "cilindrajeMayor500", nullable = true)
	private boolean cilindrajeMayor500;
	
	@Column(name = "tipoVehiculo", nullable = true)
	private String tipoVehiculo;
	
	

}
