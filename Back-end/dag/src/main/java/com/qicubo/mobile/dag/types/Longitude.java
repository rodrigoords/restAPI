package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Longitude extends Coordenada {

    /**
     * genereated serial
     */
    private static final long serialVersionUID = -7848496986150250151L;

    @Column(name = "longitude", nullable = false, precision = 19, scale = 6)
    private BigDecimal longitude;

    public Longitude() {

    }

    public Longitude(String valor) {
        longitude = new BigDecimal(valor).setScale(6, RoundingMode.DOWN);
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public int intValue() {
        return longitude.intValue();
    }

    @Override
    public long longValue() {
        return longitude.longValue();
    }

    @Override
    public float floatValue() {
        return longitude.floatValue();
    }

    @Override
    public double doubleValue() {
        return longitude.doubleValue();
    }

    @Override
    public String toString() {
        return longitude.toString();
    }

    @Override
    public Coordenada subtract(Coordenada subtractElement) {

        BigDecimal subtractBigDecimalElement = new BigDecimal(subtractElement.toString());

        return new Longitude(this.bigDecimalValue().subtract(subtractBigDecimalElement).toString());
    }

    @Override
    public Coordenada add(Coordenada addElement) {
        BigDecimal addBigDecimalElement = new BigDecimal(addElement.toString());

        return new Longitude(this.bigDecimalValue().add(addBigDecimalElement).toString());
    }
    
    @Override
    public BigDecimal bigDecimalValue() {
        return longitude;
    }
    
    @Override
    public Index indexValue() {
        return new Index(longitude.toString());
    }
    
    public Longitude newLongitude(String longi){
        return new Longitude(longi);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Longitude other = (Longitude) obj;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        } else if (!longitude.equals(other.longitude))
            return false;
        return true;
    }
    
    
}
