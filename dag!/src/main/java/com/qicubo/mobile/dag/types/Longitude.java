package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

@Embeddable
public class Longitude {
    private BigDecimal longitude;
    
    public Longitude(){
    	
    }
    
    public Longitude(String valor) {
        longitude = new BigDecimal(valor).setScale(6, RoundingMode.DOWN);
    }
    
    public BigDecimal getLongitude(){
        return longitude;
    }
    
}
