package com.qicubo.mobile.dag.services;

import java.util.List;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

public interface BolhaService extends Service<Bolha>{
	     
    List<Bolha> findBolhaByUser(Usuario user);
    
    List<Bolha> findAllCloserBolhas(Latitude latitude, Longitude longitude, Index index);
    
    public boolean isBolhaExist(Bolha bolha);
     
}
