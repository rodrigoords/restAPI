package com.qicubo.mobile.dag.daos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qicubo.mobile.dag.Boot;
import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.services.BolhaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest
public class BolhaDaoTest {
    
    private Bolha bolha;
    @Autowired
    private BolhaService bolhaService;
    
    @Test
    public void insereBolha() {
    	
        bolha = new BolhaBuilder("Test").build();
        
        bolhaService.create(bolha);
        
        
        Bolha bolhaPersistida = bolhaService.findById(bolha.getId());
        
       Assert.assertEquals(bolha.getNome(), bolhaPersistida.getNome());
       Assert.assertEquals(bolha.getDescricao(), bolhaPersistida.getDescricao());
    }

}
