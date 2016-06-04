package com.qicubo.mobile.dag.daos;

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
    public void findBolhaByUsuario() {
        bolha = new BolhaBuilder("Test").build();
        bolhaDao.save(bolha);

        List<Bolha> bolhas = bolhaDao.findBolhaByUsuario(bolha.getUsuarioCriacao().getId());

        Assert.assertEquals(bolha, bolhas.get(0));
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
}
