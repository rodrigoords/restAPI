package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

@Embeddable
public final class Latitude {
    
    private BigDecimal latitude;
    
    public Latitude(){
    	
    }
    
    public Latitude(String valor) {
        latitude = new BigDecimal(valor).setScale(6, RoundingMode.DOWN);
    }
    
    public BigDecimal getLatitude(){
        return latitude;
    }
    
}
