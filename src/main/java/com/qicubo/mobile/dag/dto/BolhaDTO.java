package com.qicubo.mobile.dag.dto;

import java.io.Serializable;

public class BolhaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private String tipoNome;
    private String usuarioCriacaoLogin;
    private String latitude;
    private String longitude;
    private String dtHoraCriacao;
    private String indRestrita;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoNome() {
        return tipoNome;
    }

    public void setTipoNome(String tipo) {
        this.tipoNome = tipo;
    }

    public String getUsuarioCriacaoLogin() {
        return usuarioCriacaoLogin;
    }

    public void setUsuarioCriacaoLogin(String usuarioLogin) {
        this.usuarioCriacaoLogin = usuarioLogin;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDtHoraCriacao() {
        return dtHoraCriacao;
    }

    public void setDtHoraCriacao(String dtHoraCricao) {
        this.dtHoraCriacao = dtHoraCricao;
    }

    public String getIndRestrita() {
        return indRestrita;
    }

    public void setIndRestrita(String indRestrita) {
        this.indRestrita = indRestrita;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtHoraCriacao == null) ? 0 : dtHoraCriacao.hashCode());
		result = prime * result + ((indRestrita == null) ? 0 : indRestrita.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoNome == null) ? 0 : tipoNome.hashCode());
		result = prime * result + ((usuarioCriacaoLogin == null) ? 0 : usuarioCriacaoLogin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BolhaDTO other = (BolhaDTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtHoraCriacao == null) {
			if (other.dtHoraCriacao != null)
				return false;
		} else if (!dtHoraCriacao.equals(other.dtHoraCriacao))
			return false;
		if (indRestrita == null) {
			if (other.indRestrita != null)
				return false;
		} else if (!indRestrita.equals(other.indRestrita))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoNome == null) {
			if (other.tipoNome != null)
				return false;
		} else if (!tipoNome.equals(other.tipoNome))
			return false;
		if (usuarioCriacaoLogin == null) {
			if (other.usuarioCriacaoLogin != null)
				return false;
		} else if (!usuarioCriacaoLogin.equals(other.usuarioCriacaoLogin))
			return false;
		return true;
	}
    
    
    
}
