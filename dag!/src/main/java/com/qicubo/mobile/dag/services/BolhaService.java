package com.qicubo.mobile.dag.services;

import java.math.BigDecimal;
import java.util.List;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;

public interface BolhaService extends Service<Bolha>{
	     
    List<Bolha> findBolhaByUser(Usuario user);
    
    List<Bolha> findAllCloserBolhas(BigDecimal latitude, BigDecimal longitude);
    
    public boolean isBolhaExist(Bolha bolha);
     
}
