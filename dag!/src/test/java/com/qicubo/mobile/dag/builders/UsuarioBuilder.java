package com.qicubo.mobile.dag.builders;

import org.springframework.stereotype.Component;

import com.qicubo.mobile.dag.models.Usuario;

@Component
public class UsuarioBuilder {
    Usuario usuario = new Usuario();
    
    public UsuarioBuilder(){
        usuario.setLogin("test");
        usuario.setNome("Teste");
        usuario.setSobreNome("Unitario");
    }
    
    public UsuarioBuilder(String login){
        usuario.setLogin(login);
        usuario.setNome("Teste");
        usuario.setSobreNome("Unitario");
    }
    
    public UsuarioBuilder comEmail(String email){
        usuario.setEmail(email);
        return this;
    }
    
    public UsuarioBuilder comSenha(String senha){
        usuario.setSenha(senha);
        return this;
    }
    
    public Usuario build(){
        return usuario;
    }
}
