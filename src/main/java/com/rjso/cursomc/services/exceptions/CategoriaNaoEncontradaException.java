package com.rjso.cursomc.services.exceptions;

public class CategoriaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoriaNaoEncontradaException(String message) {
		super(message);
	}
	
	

}
