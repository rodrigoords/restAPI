package com.qicubo.mobile.dag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = UsuarioRestURIConstants.GET_USUARIO_BY_LOGIN)
	public ResponseEntity<Usuario> getUsuarioByLogin(@PathVariable("login") String login) {
		
		Usuario usuario = usuarioService.findByLogin(login); 
		
		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuario, HttpStatus.OK);
		 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = UsuarioRestURIConstants.CREATE_USUARIO)
	public ResponseEntity<Void> createUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder ){
		
		if (usuarioService.isUsuarioExist(usuario)){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		usuarioService.create(usuario);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(UsuarioRestURIConstants.CREATE_USUARIO).buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

}
