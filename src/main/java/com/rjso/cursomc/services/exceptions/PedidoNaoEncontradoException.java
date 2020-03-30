package com.rjso.cursomc.services.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNaoEncontradoException(String message) {
		super(message);
	}
	
	

}
