package com.rjso.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.rjso.cursomc.services.exceptions.CategoriaNaoEncontradaException;
import com.rjso.cursomc.services.exceptions.ClienteNaoEncontradoException;
import com.rjso.cursomc.services.exceptions.IntegridadeDeDadosException;
import com.rjso.cursomc.services.exceptions.PedidoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<StandardError> categoriaNaoEncontrada(CategoriaNaoEncontradaException e,
			HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<StandardError> clienteNaoEncontrado(ClienteNaoEncontradoException e,
			HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validacaoDTO(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
	
	

	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<StandardError> pedidoNaoEncontrado(PedidoNaoEncontradoException e,
			HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(IntegridadeDeDadosException.class)
	public ResponseEntity<StandardError> integridadeDeDados(IntegridadeDeDadosException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
}
