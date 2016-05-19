package com.qicubo.mobile.dag.services;

import java.util.List;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;

public interface BolhaService {
	
    Bolha findById(long id);
    
    void createBolha(Bolha bolha);
     
    void updateBolha(Bolha bolha);
     
    void deleteBolhaById(long id);
 
    List<Bolha> findAllBolhas(); 
    
    List<Bolha> findBolhaByUser(Usuario user);
    
    List<Bolha> findAllCloserBolhas(Long latitude, Long longitude);
    
    public boolean isBolhaExist(Bolha bolha);
     
}
