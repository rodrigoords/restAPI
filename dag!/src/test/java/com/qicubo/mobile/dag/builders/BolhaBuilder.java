package com.qicubo.mobile.dag.builders;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Component
public class BolhaBuilder {
    
	UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
	
	TipoBuilder tipoBuilder = new TipoBuilder();
	
	Bolha bolha = new Bolha();
	
    Usuario usuario = usuarioBuilder.build();
    Tipo tipo = tipoBuilder.build();
    
    public BolhaBuilder() {
	}
    
    public BolhaBuilder(String name) {
        bolha.setNome(name);
        bolha.setDescricao("Test Unitario - Bolha Builder");
        bolha.setUsuarioCriacao(usuario);
        bolha.setTipo(tipo);
        bolha.setDtHoraCriacao(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        bolha.setLatitude(new Latitude("-22.815504"));
        bolha.setLongitude(new Longitude("-47.045347"));
        bolha.setIndRestrita(0);
    }
    
    public BolhaBuilder(String name, Usuario user){
        bolha.setNome(name);
        bolha.setUsuarioCriacao(user);
    }
    
    
    public BolhaBuilder comUsuario(Usuario user){
        bolha.setUsuarioCriacao(user);
        return this;
    }
    
    public BolhaBuilder comTipo(Tipo tipo){
        bolha.setTipo(tipo);
        return this;
    }
    
    public BolhaBuilder comLatitude(Latitude lati){
        bolha.setLatitude(lati);
        return this;
    }
    
    public BolhaBuilder comLongitude(Longitude longi){
        bolha.setLongitude(longi);
        return this;
    }
    
    public BolhaBuilder indRestrita(Integer restrita){
        bolha.setIndRestrita(restrita);
        return this;
    }
    
    public Bolha build(){
        return bolha;
    }
}
