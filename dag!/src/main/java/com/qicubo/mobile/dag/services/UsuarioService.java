package com.qicubo.mobile.dag.services;

import com.qicubo.mobile.dag.models.Usuario;

public interface UsuarioService {
	Usuario findByLogin(String login);
}
