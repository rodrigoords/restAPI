package com.qicubo.mobile.dag.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.UsuarioDao;
import com.qicubo.mobile.dag.models.Usuario;

@Transactional
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger log = Logger.getLogger(UsuarioService.class.getName());
	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public Usuario findByLogin(String login) {
		Usuario user = new Usuario();
		try {
			user = usuarioDao.findByLogin(login);
		} catch (NoResultException ex) {
		    log.log(Level.INFO, "No data found in find by login query", ex);
			user = null;
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.toString(), ex);
		}
		return user;
	}

	@Override
	public boolean isUsuarioExist(Usuario usuario){
		boolean retorno = true;
		try {
			usuarioDao.findByLogin(usuario.getLogin());
		}catch(NoResultException ex){
		    log.log(Level.INFO, "No data found in find by login query", ex);
			retorno = false;
		} catch (Exception ex) {
		    log.log(Level.SEVERE, ex.toString(), ex);
		}
		return retorno;
	};

	@Override
	public Usuario findById(Long id) {
		return usuarioDao.findById(id);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.all();
	}

	@Override
	public void create(Usuario user) {
		usuarioDao.save(user);
	}

	@Override
	public void update(Usuario usuario) {
		usuarioDao.update(usuario);

	}

	@Override
	public void deleteById(Long id) {
		usuarioDao.remove(usuarioDao.findById(id));

	}

}
