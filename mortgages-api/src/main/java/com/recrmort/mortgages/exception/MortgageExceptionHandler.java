package com.recrmort.mortgages.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.recrmort.mortgages.model.ErrorDetails;

@ControllerAdvice
@RestController
public class MortgageExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * handler for all the bad request exception types for the application
	 * 
	 * @param exception exception object
	 * @param request web request being watched to handle any exception 
	 * @return ResponseEntity containing error mesage , description and current time
	 */
	@ExceptionHandler(BadRequestDataException.class)
	public final ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestDataException exception,
			WebRequest request) {
		return new ResponseEntity<>(
				new ErrorDetails(exception.getMessage(), request.getDescription(false), LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * handler for the all other exceptions which do not explicitly catch specific errors 
	 * 
	 * @param ex exception object for all other exceptions
	 * @param request  web request being watched to handle any exception 
	 * @return ResponseEntity containing error mesage , description and current time
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false),
				LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
