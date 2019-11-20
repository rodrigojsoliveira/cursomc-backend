package com.rjso.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rjso.cursomc.services.exceptions.CategoriaNaoEncontradaException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<StandardError> categoriaNaoEncontrada(CategoriaNaoEncontradaException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
}
