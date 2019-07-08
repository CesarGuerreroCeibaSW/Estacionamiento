package com.ceiba.adn.estacionamiento.infraestructura.error;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ceiba.adn.estacionamiento.dominio.excepcion.ArgumentoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.CapacidadMaximaCarroExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.CapacidadMaximaMotoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.DiaIngresoNoPermitidoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.TipoVehiculoInvalidoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.VehiculoRegistradoExcepcion;
import com.ceiba.adn.estacionamiento.dominio.excepcion.VehiculoNoEncontradoExcepcion;


@ControllerAdvice
public class HandlerError extends ResponseEntityExceptionHandler {

	private static final ConcurrentHashMap<String, Integer> ERRORS = new ConcurrentHashMap<>();

	public HandlerError() {
		ERRORS.put(ArgumentoExcepcion.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		ERRORS.put(CapacidadMaximaCarroExcepcion.class.getSimpleName(), HttpStatus.FORBIDDEN.value());
		ERRORS.put(CapacidadMaximaMotoExcepcion.class.getSimpleName(), HttpStatus.FORBIDDEN.value());
		ERRORS.put(DiaIngresoNoPermitidoExcepcion.class.getSimpleName(), HttpStatus.NOT_ACCEPTABLE.value());
		ERRORS.put(TipoVehiculoInvalidoExcepcion.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		ERRORS.put(VehiculoRegistradoExcepcion.class.getSimpleName(), HttpStatus.NOT_ACCEPTABLE.value());
		ERRORS.put(VehiculoNoEncontradoExcepcion.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
	}

}