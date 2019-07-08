package com.ceiba.adn.estacionamiento.aplicacion.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TiqueteComando {
	
	private String placa;
	private boolean cilindrajeMayor500;
	private String tipoVehiculo;

}
