package com.qicubo.mobile.dag.services.exceptions;

public class UsuarioBolhaNullException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770822547226661393L;
	
	public UsuarioBolhaNullException(String msg){
		super(msg);
	}
	
	public UsuarioBolhaNullException(String msg, Throwable causa){
		super(msg, causa);
	}
	
}
