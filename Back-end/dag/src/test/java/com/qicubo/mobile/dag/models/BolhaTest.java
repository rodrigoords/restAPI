package com.qicubo.mobile.dag.models;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;


public class BolhaTest {

    private Bolha bolha;

    @Test
    public void distanciaMetros() {
        
        Latitude latitudeA = new Latitude("-22.798368");
        Latitude latitudeB = new Latitude("-22.801408");
        
        Longitude longitudeA = new Longitude("-47.210201");
        Longitude longitudeB = new Longitude("-47.211400");
        
        bolha = new BolhaBuilder("Test distancia entre bolhas").build();
        
        bolha.setLatitude(latitudeA);
        bolha.setLongitude(longitudeA);
        
        Assert.assertEquals(new BigDecimal("0.338"), bolha.distancia(latitudeB, longitudeB, "K")); 
    }
    
    @Test
    public void distanciaKilometros() {
        
        Latitude latitudeA = new Latitude("-22.798368");
        Latitude latitudeB = new Latitude("-22.808331");
        
        Longitude longitudeA = new Longitude("-47.210201");
        Longitude longitudeB = new Longitude("-47.214559");
        
        bolha = new BolhaBuilder("Test distancia entre bolhas").build();
        
        bolha.setLatitude(latitudeA);
        bolha.setLongitude(longitudeA);
        
        Assert.assertEquals(new BigDecimal("1.108"), bolha.distancia(latitudeB, longitudeB, "K")); 
    }
}
