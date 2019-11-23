package com.rjso.cursomc.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteNaoEncontradoException(String message) {
		super(message);
	}
	
	

}
