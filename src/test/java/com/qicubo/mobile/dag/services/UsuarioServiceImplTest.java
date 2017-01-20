package com.qicubo.mobile.dag.services;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qicubo.mobile.dag.builders.UsuarioBuilder;
import com.qicubo.mobile.dag.daos.UsuarioDao;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.exceptions.UsuarioNaoEncontradoException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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

    @Test(expected=UsuarioNaoEncontradoException.class)
    public void findUsuarioByLoginReturnNull() {
        usuario = new UsuarioBuilder().build();
        
        usuarioService.findByLogin(usuario.getLogin());        

    }
}
