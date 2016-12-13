package com.qicubo.mobile.dag.daos;

import static org.junit.Assert.fail;

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
import com.qicubo.mobile.dag.builders.UsuarioBuilder;
import com.qicubo.mobile.dag.models.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class UsuarioDaoTest {

    Usuario usuario;

    @Autowired
    UsuarioDao usuarioDao;

    @Test
    public void saveUsuario() {
        usuario = new UsuarioBuilder().build();

        usuarioDao.save(usuario);

        Usuario usuarioPersistido = usuarioDao.findById(usuario.getId());

        Assert.assertEquals(usuario, usuarioPersistido);
    }

    @Test
    public void findAllUsuario() {
        List<Usuario> usuarioBefore = usuarioDao.all();

        usuario = new UsuarioBuilder().build();

        usuarioDao.save(usuario);

        List<Usuario> usuarioAfter = usuarioDao.all();

        Assert.assertEquals(usuarioBefore.size() + 1, usuarioAfter.size());
    }

    @Test
    public void findUsuarioById() {
        usuario = new UsuarioBuilder("dagTest").build();

        usuarioDao.save(usuario);

        Usuario usuarioPersistido = usuarioDao.findById(usuario.getId());

        Assert.assertEquals(usuario.getLogin(), usuarioPersistido.getLogin());
    }

    @Test
    public void findUsuarioByLogin() throws Exception {
        final String login = "TestDao";

        usuario = new UsuarioBuilder(login).build();

        usuarioDao.save(usuario);

        Usuario usuarioPersistido = usuarioDao.findByLogin(login);

        Assert.assertEquals(usuario, usuarioPersistido);

    }

    @Test
    public void findUsuarioByLoginInexistente() {
        try {
            usuario = usuarioDao.findByLogin("TestDao");
            fail("Select must return no data");
        } catch (Exception e) {
            Assert.assertEquals("No entity found for query", e.getMessage());
        }
    }

    @Test
    public void updateUsuario() {
        final String loginTest = "TestUserDaoModified";

        usuario = new UsuarioBuilder().build();

        usuarioDao.save(usuario);

        usuario.setLogin(loginTest);

        usuarioDao.update(usuario);

        usuario = usuarioDao.findById(usuario.getId());

        Assert.assertEquals(loginTest, usuario.getLogin());
    }

    @Test
    public void deleteUsuario() {
        final String loginTest = "TestLoginDao";

        usuario = new UsuarioBuilder(loginTest).build();

        usuarioDao.save(usuario);

        usuarioDao.remove(usuario);

        try {
            usuario = usuarioDao.findByLogin(loginTest);
            fail("User must be deleted");
        } catch (Exception e) {
            Assert.assertEquals("No entity found for query", e.getMessage());
        }

    }

}
