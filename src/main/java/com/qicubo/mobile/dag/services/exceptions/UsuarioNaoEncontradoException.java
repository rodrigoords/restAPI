package com.qicubo.mobile.dag.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 184696128809932167L;
	
	public UsuarioNaoEncontradoException(String msg){
		super(msg);
	}
	
	public UsuarioNaoEncontradoException(String msg, Throwable causa){
		super(msg, causa);
	}
}
