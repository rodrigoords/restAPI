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
    
    @Column(name="indice", nullable = false, precision = 4, scale = 1)
    private BigDecimal index;
    
    /* JPA Context */
    public Index(){
        
    }
    
    public Index(String index) {
        this.index = new BigDecimal(index).setScale(1, RoundingMode.DOWN);
    }

    public BigDecimal getIndex() {
        return index;
    }

    public void setIndex(BigDecimal index) {
        this.index = index;
    }

    @Override
    public int intValue() {
        return index.intValue();
    }

    @Override
    public long longValue() {
        return index.longValue();
    }

    @Override
    public float floatValue() {
        return index.floatValue();
    }

    @Override
    public double doubleValue() {
        return index.doubleValue();
    }
    
    @Override
    public String toString(){
        return index.toString();
    }
    
    public BigDecimal bigDecimalValue(){
        return index;
    }
    
    public Index subtract(Index subtrahend){
        
        BigDecimal subtractElement = new BigDecimal(subtrahend.toString());
        
        Index indexResult = new Index (this.bigDecimalValue().subtract(subtractElement).toString());
        
        return indexResult;
    }
    
    public Index add(Index addTerm){
        BigDecimal addBigDecimalTerm = new BigDecimal(addTerm.toString());
        
        Index indexResult = new Index (this.bigDecimalValue().add(addBigDecimalTerm).toString());
        
        return indexResult;
    }

}
