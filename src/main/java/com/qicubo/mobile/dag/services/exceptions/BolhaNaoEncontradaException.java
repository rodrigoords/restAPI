package com.qicubo.mobile.dag.services.exceptions;

public class BolhaNaoEncontradaException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4291486703789993781L;

	public BolhaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public BolhaNaoEncontradaException(String msg, Throwable causa){
		super(msg, causa);
	}
}
