package com.qicubo.mobile.dag.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qicubo.mobile.dag.Boot;
import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class BolhaDaoTest {

    private Bolha bolha;
    @Autowired
    private BolhaDao bolhaDao;

    @Test
    public void findAllBolhas() {
        List<Bolha> bolhasAntes = bolhaDao.all();

        bolha = new BolhaBuilder("Test Bolha 1").build();

        bolhaDao.save(bolha);

        List<Bolha> bolhasDepois = bolhaDao.all();

        Assert.assertEquals(bolhasAntes.size() + 1, bolhasDepois.size());

    }

    @Test
    public void findBolhaById() {
        bolha = new BolhaBuilder("Test").build();
        bolhaDao.save(bolha);

        Bolha bolhaPersistida = bolhaDao.findById(bolha.getId());

        Assert.assertEquals(bolha, bolhaPersistida);
    }
    
    @Test
    public void findBolhaByIdNotExists() {
        Long idTest = 9999L;
        
        Bolha bolhaPersistida = bolhaDao.findById(idTest);

        Assert.assertEquals( null , bolhaPersistida);
    }

    @Test
    public void findBolhaByUsuario() {
        bolha = new BolhaBuilder("Test").build();
        bolhaDao.save(bolha);

        List<Bolha> bolhas = bolhaDao.findBolhaByUsuario(bolha.getUsuarioCriacao().getId());

        Assert.assertEquals(bolha, bolhas.get(0));
    }
    
    @Test
    public void findBolhaByUsuarioNotExist() {
        bolha = new BolhaBuilder("Test Not Exist").build();

        List<Bolha> bolhas = bolhaDao.findBolhaByUsuario(bolha.getUsuarioCriacao().getId());

        Assert.assertEquals(0, bolhas.size());
    }

    @Test
    public void saveBolha() {

        bolha = new BolhaBuilder("Test").build();

        bolhaDao.save(bolha);

        Bolha bolhaPersistida = bolhaDao.findById(bolha.getId());

        Assert.assertEquals(bolha, bolhaPersistida);
    }

    @Test
    public void removeBolha() {
        bolha = new BolhaBuilder("Test").build();

        bolhaDao.save(bolha);

        bolha = bolhaDao.findById(bolha.getId());

        bolhaDao.remove(bolha);

        bolha = bolhaDao.findById(bolha.getId());

        Assert.assertTrue(bolha == null);
    }

    @Test
    public void updateBolha() {
        
        final String descricaoTest = "Test Modificado";
        
        bolha = new BolhaBuilder("Test").build();
        
        bolhaDao.save(bolha);
        
        bolha.setDescricao(descricaoTest);
        
        bolhaDao.update(bolha);
        
        bolha = bolhaDao.findById(bolha.getId());
        
        Assert.assertEquals(descricaoTest, bolha.getDescricao());
    }
    
    @Test
    public void findCloserBolhasAllClose(){
        
        bolha = new BolhaBuilder("Test Index").build();
        Bolha bolha2 = new BolhaBuilder("Test Index 2").build();
        
        BigDecimal latitudeNova = bolha2.getLatitude().bigDecimalValue().add(new BigDecimal(0.002));
        BigDecimal longitudeNova = bolha2.getLongitude().bigDecimalValue().add(new BigDecimal(0.002));
        
        bolha2.setLatitude(new Latitude(latitudeNova.toString()));
        bolha2.setLongitude(new Longitude(longitudeNova.toString()));
        
        bolhaDao.save(bolha);
        bolhaDao.save(bolha2);
        
        List<Bolha> bolhas = bolhaDao.findCloserBolhas(bolha.getIndice());
        
        Assert.assertEquals(2, bolhas.size());
        
    }
    
    @Test
    public void findCloserBolhasNotAllClose(){
        
        bolha = new BolhaBuilder("Test Index").build();
        Bolha bolha2 = new BolhaBuilder("Test Index 2").build();
        
        BigDecimal latitudeNova = bolha2.getLatitude().bigDecimalValue().add(new BigDecimal("2.002"));
        BigDecimal longitudeNova = bolha2.getLongitude().bigDecimalValue().add(new BigDecimal("2.002"));
        
        bolha2.setLatitude(new Latitude(latitudeNova.toString()));
        bolha2.setLongitude(new Longitude(longitudeNova.toString()));
        
        bolhaDao.save(bolha);
        bolhaDao.save(bolha2);
        
        List<Bolha> bolhas = bolhaDao.findCloserBolhas(bolha.getIndice());
        
        Assert.assertEquals(1, bolhas.size());
    }
    
}
