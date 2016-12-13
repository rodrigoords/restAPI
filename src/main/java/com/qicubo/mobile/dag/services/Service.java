package com.qicubo.mobile.dag.services;

import java.util.List;

public interface Service<T> {
    
    List<T> findAll();
    
    T findById(Long id);
    
    T create(T entidade);
    
    void update(T entidade);
     
    void deleteById(Long id); 
    
}
