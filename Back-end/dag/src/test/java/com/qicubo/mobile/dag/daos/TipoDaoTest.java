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
import com.qicubo.mobile.dag.builders.TipoBuilder;
import com.qicubo.mobile.dag.models.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class TipoDaoTest {

    private Tipo tipo;

    @Autowired
    private TipoDao tipoDao;

    @Test
    public void findAllTipos() {

        List<Tipo> tiposBefore = tipoDao.all();

        tipo = new TipoBuilder().build();

        tipoDao.save(tipo);

        List<Tipo> tiposAfter = tipoDao.all();

        Assert.assertEquals(tiposBefore.size() + 1, tiposAfter.size());
    }

    @Test
    public void findTipoById() {
        tipo = new TipoBuilder().build();

        tipoDao.save(tipo);

        tipo = tipoDao.findById(tipo.getId());

        Assert.assertNotNull(tipo);
    }

    @Test
    public void findTipoByName() throws Exception {

        tipo = new TipoBuilder().build();

        tipoDao.save(tipo);

        Tipo tipoPersistido = tipoDao.findByNome(tipo.getNome());

        Assert.assertEquals(tipo, tipoPersistido);
    }

    @Test
    public void saveTipo() {

        tipo = new TipoBuilder().build();
        tipoDao.save(tipo);

        Tipo tipoPersistido = tipoDao.findById(tipo.getId());

        Assert.assertEquals(tipo, tipoPersistido);

    }

    @Test
    public void removeTipo() {
        tipo = new TipoBuilder().build();

        tipoDao.save(tipo);

        tipo = tipoDao.findById(tipo.getId());

        tipoDao.remove(tipo);

        tipo = tipoDao.findById(tipo.getId());

        Assert.assertNull(tipo);
    }

    @Test
    public void updateTipo() {
        final String descricaoTest = "Test Modificado";

        tipo = new TipoBuilder().build();

        tipoDao.save(tipo);

        tipo.setDescricao(descricaoTest);

        tipoDao.update(tipo);

        tipo = tipoDao.findById(tipo.getId());

        Assert.assertEquals(descricaoTest, tipo.getDescricao());
    }

}
