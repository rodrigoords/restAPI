package com.qicubo.mobile.dag.services;

import com.qicubo.mobile.dag.models.Usuario;

public interface UsuarioService extends Service<Usuario>{
	Usuario findByLogin(String login);
	boolean isUsuarioExist(Usuario usuario);
}
