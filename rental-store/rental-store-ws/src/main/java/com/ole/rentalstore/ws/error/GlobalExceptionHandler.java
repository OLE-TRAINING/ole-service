package com.ole.rentalstore.ws.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.bad_request.BadRequestException;
import com.ole.rentalstore.commons.exceptions.conflict.ConflitException;
import com.ole.rentalstore.commons.exceptions.not_found.NotFoundException;
import com.ole.rentalstore.commons.exceptions.unauthorized.UnauthorizedException;

@ControllerAdvice
@PropertySource(value="classpath:error-messages.properties", encoding="UTF-8")
public class GlobalExceptionHandler {

	@Autowired
	private Environment env;
	
	private ErrorResponse buildErrorResponse(String key) {
		return new ErrorResponse(key, env.getProperty(key));
	}
	
	@ExceptionHandler(value = { BadRequestException.class })
	public ResponseEntity<Object> handleBadRequests(BadRequestException ex) {
		ErrorResponse error = buildErrorResponse(ex.getErrorResponse().getKey());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { ConflitException.class })
	public ResponseEntity<Object> handleConflicts(ConflitException ex) {
		ErrorResponse error = buildErrorResponse(ex.getErrorResponse().getKey());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { UnauthorizedException.class })
	public ResponseEntity<Object> handleUnauthorizations(UnauthorizedException ex) {
		ErrorResponse error = buildErrorResponse(ex.getErrorResponse().getKey());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<Object> handleResourcesNotFound(NotFoundException ex) {
		ErrorResponse error = buildErrorResponse(ex.getErrorResponse().getKey());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
		String message = env.getProperty(ErrorMessage.UNEXPECTED_ERROR);
		ErrorResponse error = new ErrorResponse(ErrorMessage.UNEXPECTED_ERROR, message);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}