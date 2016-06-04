package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class Latitude extends Coordenada{
    
    /**
     * Genereted serial
     */
    private static final long serialVersionUID = -7060033894245105818L;
    
    
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

    @Override
    public int intValue() {
        return latitude.intValue();
    }

    @Override
    public long longValue() {
        return latitude.longValue();
    }

    @Override
    public float floatValue() {
        return latitude.floatValue();
    }

    @Override
    public double doubleValue() {
        return latitude.doubleValue();
    }
    
    @Override
    public String toString(){
        return latitude.toString();
    }

    @Override
    public Coordenada subtract(Coordenada subtractElement) {
        BigDecimal subtractBigDecimalElement = new BigDecimal(subtractElement.toString());
        
        return new Latitude (this.bigDecimalValue().subtract(subtractBigDecimalElement).toString());
    }

    @Override
    public Coordenada add(Coordenada addElement) {
        BigDecimal addBigDecimalElement = new BigDecimal(addElement.toString());
        
        return new Latitude(this.bigDecimalValue().add(addBigDecimalElement).toString());
    }
    
    @Override
    public BigDecimal bigDecimalValue(){
        return latitude;
    }
    
    @Override
    public Index indexValue(){
        return new Index(latitude.toString());
    }
    
}
