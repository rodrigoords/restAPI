package com.qicubo.mobile.dag.models;

import org.springframework.http.HttpStatus;

public class DetalhesErro {
	
	private String titulo;
	private HttpStatus status;
	private Long timestamp;
	private String msgDetalhes;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getMsgDetalhes() {
		return msgDetalhes;
	}
	public void setMsgDetalhes(String msgDetalhes) {
		this.msgDetalhes = msgDetalhes;
	}
	
}
