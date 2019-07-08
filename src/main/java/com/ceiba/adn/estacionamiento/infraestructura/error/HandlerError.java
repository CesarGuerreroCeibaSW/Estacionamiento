package com.ceiba.adn.estacionamiento.infraestructura.error;

import java.util.concurrent.ConcurrentHashMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

	private static final Logger LOG = LoggerFactory.getLogger(HandlerError.class);

	private static final String MESSAGE_ALL_ERROR = "Ocurrio un error favor contactar al administrador.";

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

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
		ResponseEntity<Error> response;
		String exceptionName = exception.getClass().getSimpleName();
		String message = exception.getMessage();

		Integer code = ERRORS.get(exceptionName);

		if (code == null) {
			LOG.error(exceptionName, message);
			Error error = new Error(exceptionName, MESSAGE_ALL_ERROR);
			response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Error error = new Error(exceptionName, message);
			response = new ResponseEntity<>(error, HttpStatus.valueOf(code));
		}

		return response;
	}
}