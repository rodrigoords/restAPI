package com.qicubo.mobile.dag.daos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.models.Bolha;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationContext.class)
@WebAppConfiguration
public class BolhaDaoTest {
    
    private Bolha bolha;
    @Autowired
    private BolhaDao bolhaDao;
    @Test
    public void insereBolha() {
        bolha = new BolhaBuilder("Test").build();
        
        bolhaDao.save(bolha);
        
        Bolha bolhaPersistida = bolhaDao.findById(bolha.getId());
        
        Assert.assertNotNull(bolhaPersistida);
    }

}
