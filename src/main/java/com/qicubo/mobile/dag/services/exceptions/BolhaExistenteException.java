package com.qicubo.mobile.dag.services.exceptions;

public class BolhaExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 182966657748249505L;
	
	public BolhaExistenteException(String msg) {
		super(msg);
	}
	
	public BolhaExistenteException(String msg, Throwable causa){
		super(msg, causa);
	}
}
