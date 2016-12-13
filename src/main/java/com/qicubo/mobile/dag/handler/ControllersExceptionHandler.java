package com.qicubo.mobile.dag.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qicubo.mobile.dag.models.DetalhesErro;
import com.qicubo.mobile.dag.services.exceptions.BolhaExistenteException;
import com.qicubo.mobile.dag.services.exceptions.BolhaNaoEncontradaException;

@ControllerAdvice
public class ControllersExceptionHandler {

	@ExceptionHandler(BolhaNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleBolhaNaoEncontradaException(BolhaNaoEncontradaException e, HttpServletRequest request){
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.NOT_FOUND);
		detalhesErro.setTitulo("Bolha não pode ser encontrada");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/404");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
	
	@ExceptionHandler(BolhaExistenteException.class)
	public ResponseEntity<DetalhesErro> handleBolhaExistenteException(BolhaExistenteException e, HttpServletRequest request){
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.CONFLICT);
		detalhesErro.setTitulo("A bolha já existe");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/409");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
}
