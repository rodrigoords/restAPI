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

}
