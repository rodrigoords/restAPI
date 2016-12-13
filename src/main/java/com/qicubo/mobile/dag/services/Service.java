package com.qicubo.mobile.dag.services;

import java.util.List;

public interface Service<T> {
    
    List<T> findAll();
    
    T findById(Long id);
    
    void create(T bolha);
    
    void update(T bolha);
     
    void deleteById(Long id); 
    
}
