package com.qicubo.mobile.dag.services;

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
import com.qicubo.mobile.dag.builders.UsuarioBuilder;
import com.qicubo.mobile.dag.daos.UsuarioDao;
import com.qicubo.mobile.dag.models.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class UsuarioServiceImplTest {

    private Usuario usuario;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private UsuarioService usuarioService;
    
    @Test
    public void isUsuarioExistsTrue() {
        usuario = new UsuarioBuilder("testUsuarioExists").build();
        usuarioDao.save(usuario);
        
        Assert.assertTrue(usuarioService.isUsuarioExist(usuario));
    }

    @Test
    public void isUsuarioExistsFalse() {
        usuario = new UsuarioBuilder().build();
        
        Assert.assertFalse(usuarioService.isUsuarioExist(usuario));
    }

    @Test
    public void findUsuarioByLoginReturnUser() {
        usuario = new UsuarioBuilder("testUsuarioExists").build();
        usuarioDao.save(usuario);
        
        Usuario usuarioPersistido = usuarioService.findByLogin(usuario.getLogin());
        
        Assert.assertTrue(usuario.equals(usuarioPersistido));
    }

    @Test
    public void findUsuarioByLoginReturnNull() {
        usuario = new UsuarioBuilder().build();
        
        Assert.assertNull(usuarioService.findByLogin(usuario.getLogin()));        

    }
}
