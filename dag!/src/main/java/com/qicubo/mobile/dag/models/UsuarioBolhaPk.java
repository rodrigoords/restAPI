package com.qicubo.mobile.dag.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioBolhaPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4440615076933143897L;

	@Column(name = "id_bolha")
	private Integer idBolha;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UsuarioBolhaPk) {
			UsuarioBolhaPk usuarioBolhaPk = (UsuarioBolhaPk) obj;

			if (!usuarioBolhaPk.getIdBolha().equals(idBolha)) {
				return false;
			}

			if (!usuarioBolhaPk.getIdUsuario().equals(idUsuario)) {
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return idBolha.hashCode() + idUsuario.hashCode();
	}

	public Integer getIdBolha() {
		return idBolha;
	}

	public void setIdBolha(Integer idBolha) {
		this.idBolha = idBolha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
