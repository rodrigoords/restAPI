package com.qicubo.mobile.dag.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dag_tipo")
public class Tipo {
	
	public Tipo() {
	}
	
	public Tipo(String nome) {
		this.nome = nome;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_tipo")
	private Long id;
	private String nome;
	private String descricao;
	
	@OneToMany
	@JoinColumn(name = "id_tipo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}