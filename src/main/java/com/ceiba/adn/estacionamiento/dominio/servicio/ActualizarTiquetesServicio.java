package com.ceiba.adn.estacionamiento.dominio.servicio;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ceiba.adn.estacionamiento.dominio.entidad.Tiquete;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoActualizarRepositorio;
import com.ceiba.adn.estacionamiento.dominio.repositorio.EstacionamientoConsultarRepositorio;

public class ActualizarTiquetesServicio {

	private EstacionamientoActualizarRepositorio actualizarRepositorio;
	private EstacionamientoConsultarRepositorio consultarRepositorio;
	
	private static final float VALOR_HORA_CARRO = 1000;
	private static final float VALOR_HORA_MOTO = 500;
	private static final float VALOR_DIA_CARRO = 8000;
	private static final float VALOR_DIA_MOTO = 4000;
	private static final float CILINDRAJE_GRANDE = 2000;
	private static final String MOTO = "MOTO";
	
	public ActualizarTiquetesServicio(EstacionamientoActualizarRepositorio actualizarRepositorio,
			EstacionamientoConsultarRepositorio consultarRepositorio) {
		this.actualizarRepositorio = actualizarRepositorio;
		this.consultarRepositorio = consultarRepositorio;
	}
	
	
	
	public float registrarSalida(String placa) {
		Tiquete tiquete = this.consultarRepositorio.buscarVehiculo(placa);
		tiquete.setFechaSalida(new Date());
		tiquete.setPrecio(this.calcularPrecio(tiquete));
		tiquete.setEstadoIngreso(false);
		this.actualizarRepositorio.registrarSalida(tiquete);
		return tiquete.getPrecio();
	}
	
	public float calcularPrecio(Tiquete tiquete) {
		
		float precio = 0;
		long tiempoEnEstacionamiento = tiquete.getFechaSalida().getTime() - tiquete.getFechaEntrada().getTime();
		long minutos = TimeUnit.MILLISECONDS.toMinutes(tiempoEnEstacionamiento);
		tiempoEnEstacionamiento = TimeUnit.MILLISECONDS.toHours(tiempoEnEstacionamiento);
		long dias = (tiempoEnEstacionamiento / 24);
		long horasSobrantes = (tiempoEnEstacionamiento-(dias*24));
		
		if(minutos%60 >0) {
			horasSobrantes++;
		}
		
		if(horasSobrantes>=9) {
			dias ++;
			horasSobrantes=0;
		}
		
		if(horasSobrantes==0 && dias==0) {
			horasSobrantes=1;
		}
		
		if (tiquete.getTipoVehiculo().equalsIgnoreCase(MOTO)) {
			precio += dias * VALOR_DIA_MOTO;
			precio += horasSobrantes * VALOR_HORA_MOTO;
			
			if(tiquete.isCilindrajeMayor500()) {
				precio += CILINDRAJE_GRANDE;	
			}
		} else {
			precio += dias * VALOR_DIA_CARRO;
			precio += horasSobrantes * VALOR_HORA_CARRO;
		}
		
		return precio;
	}
	
	
}
