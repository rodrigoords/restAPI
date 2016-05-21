package com.qicubo.mobile.dag.services;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.UsuarioDao;
import com.qicubo.mobile.dag.models.Usuario;

@Transactional
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public Usuario findByLogin(String login) {
		Usuario user = new Usuario();
		try {
			user = usuarioDao.findByLogin(login);
		} catch (NoResultException e) {
			user = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isUsuarioExist(Usuario usuario){
		boolean retorno = true;
		try {
			usuarioDao.findByLogin(usuario.getLogin());
		}catch(NoResultException ex){
			retorno = false;
		} catch (Exception e) {
			e.printStackTrace();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}
