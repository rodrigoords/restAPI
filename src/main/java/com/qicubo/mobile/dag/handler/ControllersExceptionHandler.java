package com.qicubo.mobile.dag.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qicubo.mobile.dag.models.DetalhesErro;
import com.qicubo.mobile.dag.services.exceptions.BolhaExistenteException;
import com.qicubo.mobile.dag.services.exceptions.BolhaNaoEncontradaException;
import com.qicubo.mobile.dag.services.exceptions.TipoBolhaNullException;
import com.qicubo.mobile.dag.services.exceptions.TipoNaoEncontradoException;
import com.qicubo.mobile.dag.services.exceptions.UsuarioBolhaNullException;

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
	
	@ExceptionHandler(TipoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleTipoNaoEncontradoException(TipoNaoEncontradoException e, HttpServletRequest resques){
		
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.NOT_FOUND);
		detalhesErro.setTitulo("Tipo não pode ser encontrada");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/404");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
	
	@ExceptionHandler(UsuarioBolhaNullException.class)
	public ResponseEntity<DetalhesErro> handleUsuarioBolhaNullException(UsuarioBolhaNullException e, HttpServletRequest request){
		
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.FAILED_DEPENDENCY);
		detalhesErro.setTitulo("Usuario invalido ou não infomado!");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/424");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
		
	}
	
	@ExceptionHandler(TipoBolhaNullException.class)
	public ResponseEntity<DetalhesErro> handleTipoBolhaNullException(TipoBolhaNullException e, HttpServletRequest request){
		
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.FAILED_DEPENDENCY);
		detalhesErro.setTitulo("Tipo da bolha invalido ou não infomado!");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/424");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<DetalhesErro> handleNumberFormatException(NumberFormatException e, HttpServletRequest request){
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.BAD_REQUEST);
		detalhesErro.setTitulo("Tipo ou formatação invalida para resquest.");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/400");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<DetalhesErro> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest resquest){
		DetalhesErro detalhesErro = new DetalhesErro();
		
		detalhesErro.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
		detalhesErro.setTitulo("Metodo não permitido");
		detalhesErro.setMsgDetalhes("http://qisi.com.br/doc/erro/405");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(detalhesErro.getStatus()).body(detalhesErro);
	}
	
}
