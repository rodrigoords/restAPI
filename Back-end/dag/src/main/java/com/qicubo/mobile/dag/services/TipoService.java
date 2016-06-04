package com.qicubo.mobile.dag.services;

import java.util.List;

import com.qicubo.mobile.dag.models.Tipo;

public interface TipoService {
	
    Tipo findById(Long id);
     
    Tipo findByName(String name);
     
    List<Tipo> findAll(); 
     
    public boolean isTipoExist(Tipo tipo);

}
