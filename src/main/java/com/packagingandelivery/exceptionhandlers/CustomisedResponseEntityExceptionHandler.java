package com.packagingandelivery.exceptionhandlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.packagingandelivery.exceptions.ComponentChargeDetailsNotFoundException;
import com.packagingandelivery.exceptions.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ComponentChargeDetailsNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleComponentChargeDetailsNotFoundException(
			ComponentChargeDetailsNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getParameter("componentType"), request.getParameter("count"));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(
			javax.validation.ConstraintViolationException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getParameter("componentType"), request.getParameter("count"));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
