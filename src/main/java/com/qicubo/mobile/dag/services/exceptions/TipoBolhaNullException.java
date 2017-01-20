package com.qicubo.mobile.dag.services.exceptions;

public class TipoBolhaNullException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3670619918234132735L;
	
	public TipoBolhaNullException(String msg){
		super(msg);
	}
	
	public TipoBolhaNullException(String msg, Throwable causa){
		super(msg, causa);
	}
	
}
