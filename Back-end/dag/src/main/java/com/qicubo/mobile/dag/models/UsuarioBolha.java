package com.qicubo.mobile.dag.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dag_usuario_bolha")
public class UsuarioBolha implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected UsuarioBolhaPk usuarioBolhaPk;

	@JoinColumn(name = "id_bolha", referencedColumnName = "id_bolha", insertable = false, updatable = false)
	@ManyToOne
	private Bolha bolha;

	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
	@OneToOne
	private Usuario usuario;

	@Column
	private String indAtivo;

	public Bolha getBolha() {
		return bolha;
	}

	public void setBolha(Bolha bolha) {
		this.bolha = bolha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIndAtivo() {
		return indAtivo;
	}

	public void setIndAtivo(String indAtivo) {
		this.indAtivo = indAtivo;
	}
}
