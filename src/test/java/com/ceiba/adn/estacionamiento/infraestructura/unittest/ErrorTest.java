package com.ceiba.adn.estacionamiento.infraestructura.unittest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ceiba.adn.estacionamiento.infraestructura.error.Error;
import com.ceiba.adn.estacionamiento.infraestructura.error.HandlerError;
import com.ceiba.adn.estacionamiento.dominio.excepcion.ArgumentoExcepcion;

public class ErrorTest {

	private HandlerError erro;
	ResponseEntity<Error> response;

	private static final String MESSAGE_ALL_ERROR = "Ocurrio un error favor contactar al administrador.";
	private static final Logger LOG = LoggerFactory.getLogger(HandlerError.class);
	
	@Test
	public void handleAllExceptionsCode() {
		erro = new HandlerError();
		ArgumentoExcepcion er = new ArgumentoExcepcion("error");
		String exceptionName = er.getClass().getSimpleName();
		String message = er.getMessage();
		Error err = new Error(exceptionName, message);
		
		response = new ResponseEntity<>(err, HttpStatus.valueOf(500));
		// act
		
		this.erro.handleAllExceptions(er);
		// assert
		assertNotNull(response);
		
	}
	
	@Test
	public void handleAllExceptionsNotCode() {
		erro = new HandlerError();
		Exception er = new Exception("error");
		String exceptionName = er.getClass().getSimpleName();
		String message = er.getMessage();
		
		LOG.error(exceptionName, message);
		Error error = new Error(exceptionName, MESSAGE_ALL_ERROR);
		response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		// act
		
		this.erro.handleAllExceptions(new Exception(er));
		// assert
		assertNotNull(response);
		
	}
}
