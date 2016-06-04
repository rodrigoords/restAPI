package com.qicubo.mobile.dag.models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Entity
@Table(name = "dag_bolha")
public class Bolha {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_bolha")
	private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_usuario")
	private Usuario usuarioCriacao;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;
	private String nome;
	private String descricao;
	private String dtHoraCriacao;
	private Latitude latitude;
	private Longitude longitude;
	private BigDecimal indice; 
	private Integer indRestrita;
	
	public Bolha(){
	}
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
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
	
	public String getDtHoraCriacao() {
		return dtHoraCriacao;
	}
	public void setDtHoraCriacao(String dtHoraCriacao) {
		this.dtHoraCriacao = dtHoraCriacao;
	}
	
	public Latitude getLatitude() {
		return latitude;
	}
	public void setLatitude(Latitude latitude) {
		this.latitude = latitude;
	}
	
	public Longitude getLongitude() {
		return longitude;
	}
	public void setLongitude(Longitude longitude) {
		this.longitude = longitude;
	}
	
	public BigDecimal getIndice() {
        return indice;
    }

    public void setIndice(BigDecimal indice) {
        this.indice = indice;
    }

    public Integer getIndRestrita() {
		return indRestrita;
	}
	public void setIndRestrita(Integer indRestrita) {
		this.indRestrita = indRestrita;
	}

}
