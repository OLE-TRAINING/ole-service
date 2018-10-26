package com.undefined.commons.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.undefined.commons.exceptions.BadRequestException;
import com.undefined.commons.exceptions.ConflitException;
import com.undefined.commons.exceptions.UnauthorizedException;

@ControllerAdvice
@PropertySource("classpath:error-messages.properties")
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
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(Exception ex) {
		String message = env.getProperty(ErrorMessage.UNEXPECTED_ERROR);
		ErrorResponse error = new ErrorResponse(ErrorMessage.UNEXPECTED_ERROR, message);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}