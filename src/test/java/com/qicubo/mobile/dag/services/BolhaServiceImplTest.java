package com.qicubo.mobile.dag.services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.daos.BolhaDao;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.services.exceptions.BolhaExistenteException;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BolhaServiceImplTest {
    
    private Bolha bolha;
    @Autowired
    private BolhaDao bolhaDao;
    @Autowired
    private BolhaService bolhaService;
    
    @Test(expected = BolhaExistenteException.class)
    public void isBolhaExistTrue(){
        bolha = new BolhaBuilder("Test Bolha Exist").build();
        bolhaDao.save(bolha);
        bolhaService.bolhaExist(bolha);
    }
    
    @Test
    public void isBolhaExistFalse(){
        bolha = new BolhaBuilder("Test Bolha not Exists").build();
        bolhaService.bolhaExist(bolha);
    }
    
    @Test
    public void findAllCloserBolhasWithBolhas(){
        Latitude  latitudeBolha = new Latitude("-22.808331");
        Longitude longitudeBolha = new Longitude("-47.214559");
        
        Latitude  latitudePoint = new Latitude("-22.798368");
        Longitude longitudePoint = new Longitude("-47.210201");
        
        bolha = new BolhaBuilder("Bolha").build();
        
        bolha.setLatitude(latitudeBolha);
        bolha.setLongitude(longitudeBolha);
        
        
        bolhaDao.save(bolha);
        
        List<Bolha> bolhas = bolhaService.findAllCloserBolhas(latitudePoint, longitudePoint);
        
        Assert.assertEquals(1, bolhas.size());
    }
    
    @Test
    public void findAllCloserBolhasWithoutBolhas(){
        Latitude latitudePoint = new Latitude("-22.798368");
        Latitude latitudeBolha = new Latitude("-23.556591");
        
        Longitude longitudePoint = new Longitude("-47.210201");
        Longitude longitudeBolha = new Longitude("-46.634875");
        
        bolha = new BolhaBuilder("Bolha").build();
        
        bolha.setLatitude(latitudeBolha);
        bolha.setLongitude(longitudeBolha);
       
        bolhaDao.save(bolha);
        
        List<Bolha> bolhas = bolhaService.findAllCloserBolhas(latitudePoint, longitudePoint);
        
        Assert.assertEquals(0, bolhas.size());        
    }
}
