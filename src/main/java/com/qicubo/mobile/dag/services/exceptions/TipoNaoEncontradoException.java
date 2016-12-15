package com.qicubo.mobile.dag.services.exceptions;

public class TipoNaoEncontradoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8688632009028399829L;
	
	public TipoNaoEncontradoException(String msg){
		super(msg);
	}
	
	public TipoNaoEncontradoException(String msg, Throwable causa){
		super(msg, causa);
	}
}
