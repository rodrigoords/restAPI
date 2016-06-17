package com.qicubo.mobile.dag.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qicubo.mobile.dag.builders.UsuarioBuilder;
import com.qicubo.mobile.dag.dto.UsuarioDTO;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.UsuarioService;
import com.qicubo.mobile.dag.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

	@InjectMocks
	private UsuarioController usuarioController;
	
	@Mock
	private UsuarioService usuarioService;
	
	private MockMvc mockMvc;
	
	private Usuario usuario;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
		usuario = new UsuarioBuilder("Teste API Rest").build();
	}
	
	@Test
	public void getUsuarioByLogin() throws Exception {
		
		Mockito.when(usuarioService.findByLogin(usuario.getLogin())).thenReturn(usuario);
		
		mockMvc.perform(get(UsuarioRestURIConstants.GET_USUARIO_BY_LOGIN, usuario.getLogin()))
																		.andExpect(status().isOk())
																		.andExpect(jsonPath("$.login", Matchers.is(usuario.getLogin())))
																		.andExpect(jsonPath("$.nome", Matchers.is(usuario.getNome())))
																		.andExpect(jsonPath("$.sobreNome", Matchers.is(usuario.getSobreNome())))
																		.andExpect(jsonPath("$.email", Matchers.is(usuario.getEmail())));
		
		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(usuario.getLogin());
		Mockito.verifyNoMoreInteractions(usuarioService);
	}
	
	@Test
	public void getUsuarioByLoginNoContent() throws Exception{
		
		Mockito.when(usuarioService.findByLogin(usuario.getLogin())).thenReturn(null);
		
		mockMvc.perform(get(UsuarioRestURIConstants.GET_USUARIO_BY_LOGIN, usuario.getLogin()))
																		.andExpect(status().isNoContent());
		
		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(usuario.getLogin());
		Mockito.verifyNoMoreInteractions(usuarioService);
	}
	
	@Test
	public void createUsuario() throws Exception{
		
		Usuario usuario = new UsuarioBuilder("TestApiRest").build();
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		Mockito.when(usuarioService.isUsuarioExist(usuario)).thenReturn(false);
		
        mockMvc.perform(post(UsuarioRestURIConstants.CREATE_USUARIO).contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
  				.andExpect(status().isCreated());
        
        Mockito.verify(usuarioService, Mockito.times(1)).isUsuarioExist(usuario);
        Mockito.verify(usuarioService, Mockito.times(1)).create(usuario);
        Mockito.verifyNoMoreInteractions(usuarioService);
        
	}
	
	@Test
	public void createUsuarioExists() throws Exception{
		
		Usuario usuario = new UsuarioBuilder("TestApiRest").build();
		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
		
		Mockito.when(usuarioService.isUsuarioExist(usuario)).thenReturn(true);
		
        mockMvc.perform(post(UsuarioRestURIConstants.CREATE_USUARIO).contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
  				.andExpect(status().isConflict());
        
        Mockito.verify(usuarioService, Mockito.times(1)).isUsuarioExist(usuario);
        Mockito.verify(usuarioService, Mockito.times(0)).create(usuario);
        Mockito.verifyNoMoreInteractions(usuarioService);
        
	}

}
