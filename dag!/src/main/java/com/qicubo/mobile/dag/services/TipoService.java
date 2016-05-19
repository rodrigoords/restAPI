package com.qicubo.mobile.dag.services;

import java.util.List;

import com.qicubo.mobile.dag.models.Tipo;

public interface TipoService {
	
    Tipo findById(Long id);
     
    List<Tipo> findByName(String name);
     
    List<Tipo> findAllTipos(); 
     
    public boolean isTipoExist(Tipo tipo);

}
