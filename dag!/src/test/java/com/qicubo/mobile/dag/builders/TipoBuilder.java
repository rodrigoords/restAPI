package com.qicubo.mobile.dag.builders;

import com.qicubo.mobile.dag.models.Tipo;


public class TipoBuilder{
    
    Tipo tipo = new Tipo();
	
    public TipoBuilder() {
       tipo.setNome("Test");
       tipo.setDescricao("Testes unitarios");
    }
    
    public TipoBuilder(String nome){
        tipo.setNome(nome);
        tipo.setDescricao("Testes unitarios");
    }
    
    public TipoBuilder(String nome, String descricao){
        tipo.setNome(nome);
        tipo.setDescricao(descricao);
    }
    
    public Tipo build() {
        return tipo;
    }
}
