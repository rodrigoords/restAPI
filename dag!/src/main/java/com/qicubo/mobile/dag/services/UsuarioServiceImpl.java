package com.qicubo.mobile.dag.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.models.Usuario;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{

	@Override
	public Usuario findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Usuario findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void create(Usuario bolha) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Usuario bolha) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

}
