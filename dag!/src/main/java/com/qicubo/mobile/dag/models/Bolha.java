package com.qicubo.mobile.dag.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dag_bolha")
public class Bolha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_bolha")
	private Integer id;
    @OneToOne
    @JoinColumn(name="id_usuario")
	private Usuario usuarioCriacao;
	@ManyToOne
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;
	private String nome;
	private String descricao;
	private String dtHoraCriacao;
	private Long latitude;
	private Long longitude;
	private Integer indRestrita;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
	public Long getLongitude() {
		return longitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	public Integer getIndRestrita() {
		return indRestrita;
	}
	public void setIndRestrita(Integer indRestrita) {
		this.indRestrita = indRestrita;
	}

}
