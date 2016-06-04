package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class Latitude {
    
    @Column(name="latitude", nullable = false, precision = 19, scale = 6)
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
