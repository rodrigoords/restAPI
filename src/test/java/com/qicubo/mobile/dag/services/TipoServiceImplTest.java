package com.qicubo.mobile.dag.services;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qicubo.mobile.dag.builders.TipoBuilder;
import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class TipoServiceImplTest {
    
    private Tipo tipo;
    @Autowired
    private TipoDao tipoDao;
    @Autowired
    private TipoService tipoService;
    
    @Test
    public void isTipoExistsTrue(){
        tipo = new TipoBuilder().build();
        tipoDao.save(tipo);
        Assert.assertTrue(tipoService.isTipoExist(tipo));
    }
    
    @Test
    public void isTipoExistsFalse(){
        tipo = new TipoBuilder().build();
        Assert.assertFalse(tipoService.isTipoExist(tipo));
    }
    
}
