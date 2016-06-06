package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Index extends Number{
    
    /**
     * genereted serial
     */
    private static final long serialVersionUID = -5539760635308261633L;
    
    @Column(name="indice", nullable = false, precision = 4, scale = 0)
    private BigDecimal indice;
    
    /* JPA Context */
    public Index(){
        
    }
    
    public Index(String index) {
        this.indice = new BigDecimal(index).setScale(0, RoundingMode.DOWN);
    }

    public BigDecimal getIndex() {
        return indice;
    }

    public void setIndex(BigDecimal index) {
        this.indice = index;
    }

    @Override
    public int intValue() {
        return indice.intValue();
    }

    @Override
    public long longValue() {
        return indice.longValue();
    }

    @Override
    public float floatValue() {
        return indice.floatValue();
    }

    @Override
    public double doubleValue() {
        return indice.doubleValue();
    }
    
    @Override
    public String toString(){
        return indice.toString();
    }
    
    public BigDecimal bigDecimalValue(){
        return indice;
    }
    
    public Index subtract(Index subtrahend){
        
        BigDecimal subtractElement = new BigDecimal(subtrahend.toString());
        
        return new Index (this.bigDecimalValue().subtract(subtractElement).toString());
        
    }
    
    public Index add(Index addTerm){
        BigDecimal addBigDecimalTerm = new BigDecimal(addTerm.toString());
        
        return new Index (this.bigDecimalValue().add(addBigDecimalTerm).toString());
        
    }

}
