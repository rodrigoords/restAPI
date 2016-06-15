package com.qicubo.mobile.dag.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.services.BolhaService;
import com.qicubo.mobile.dag.services.UsuarioService;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@RunWith(MockitoJUnitRunner.class)
public class BolhaControllerTest {
	
	@InjectMocks
	private BolhaController bolhaController;
	
	@Mock
	private BolhaService bolhaService;
	
	@Mock
	private UsuarioService usuarioService;
	
	private MockMvc mockMvc;
	
	private List<Bolha> listaBolhas = new ArrayList<>();
	
	@Before
	public void setUp(){
		
		mockMvc = MockMvcBuilders.standaloneSetup(bolhaController).build();
		
		Bolha bolhaA = new BolhaBuilder("Bolha A Test").build();
		Bolha bolhaB = new BolhaBuilder("Bolha B Test").build();
		Bolha bolhaC = new BolhaBuilder("Bolha C Test").build();
		
		bolhaB.setLatitude(new Latitude("-22.798368"));
		bolhaB.setLongitude(new Longitude("-47.210201"));
		
		bolhaC.setLatitude(new Latitude("-22.808331"));
		bolhaC.setLongitude(new Longitude("-47.214559"));
		
		bolhaA.setId(1L);
		bolhaB.setId(2L);
		bolhaC.setId(3L);
		
		listaBolhas.addAll(Arrays.asList(bolhaA, bolhaB, bolhaC));
	}
	
	@Test
	public void getAllBolhas() throws Exception {
		Mockito.when(bolhaService.findAll()).thenReturn(listaBolhas);
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_ALL_BOLHAS))
												 .andExpect(status().isOk())
												 .andExpect(jsonPath("$", Matchers.hasSize(3)))
												 .andExpect(jsonPath("$[*].id", Matchers.containsInAnyOrder(listaBolhas.get(0).getId().intValue(),
														 													listaBolhas.get(1).getId().intValue(),
														 													listaBolhas.get(2).getId().intValue())))
												 .andExpect(jsonPath("$[*].nome", Matchers.containsInAnyOrder(listaBolhas.get(0).getNome(),
														 													  listaBolhas.get(1).getNome(),
														 													  listaBolhas.get(2).getNome())));
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void getAllBolhasNoContent() throws Exception{
		Mockito.when(bolhaService.findAll()).thenReturn(new ArrayList<Bolha>());
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_ALL_BOLHAS)).andExpect(status().isNoContent());
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void getBolhaById() throws Exception{
		
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		
		Mockito.when(bolhaService.findById(bolha.getId())).thenReturn(bolha);
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_BY_ID, bolha.getId()))
											     .andExpect(status().isOk())
											     .andExpect(jsonPath("$.id", Matchers.is(bolha.getId().intValue())))
											     .andExpect(jsonPath("$.nome", Matchers.is(bolha.getNome())))
											     .andExpect(jsonPath("$.descricao", Matchers.is(bolha.getDescricao())))
											     .andExpect(jsonPath("$.usuarioCriacao.login", Matchers.is(bolha.getUsuarioCriacao().getLogin())))
											     .andExpect(jsonPath("$tipo.nome", Matchers.is(bolha.getTipo().getNome())))
											     .andExpect(jsonPath("$.dtHoraCriacao", Matchers.is(bolha.getDtHoraCriacao())))
											     .andExpect(jsonPath("$.latitude", Matchers.hasToString(bolha.getLatitude().toString())))
											     .andExpect(jsonPath("$.longitude", Matchers.hasToString(bolha.getLongitude().toString())))
											     .andExpect(jsonPath("$.indice", Matchers.hasToString(bolha.getIndice().toString())))
											     .andExpect(jsonPath("$.indRestrita", Matchers.is(bolha.getIndRestrita())));

		Mockito.verify(bolhaService, Mockito.times(1)).findById(bolha.getId());
		Mockito.verifyNoMoreInteractions(bolhaService);		
	}
	
	@Test
	public void getBolhaByIdNoContent() throws Exception{
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		
		Mockito.when(bolhaService.findById(bolha.getId())).thenReturn(null);
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_BY_ID, bolha.getId()))
											     .andExpect(status().isNoContent());
											     
		Mockito.verify(bolhaService, Mockito.times(1)).findById(bolha.getId());
		Mockito.verifyNoMoreInteractions(bolhaService);	
	}
	
	@Test
	public void getBolhaByUser() throws Exception{
		
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		List<Bolha> bolhas = Arrays.asList(bolha);
		
		Mockito.when(bolhaService.findBolhaByUser(bolha.getUsuarioCriacao())).thenReturn(bolhas);
		Mockito.when(usuarioService.findByLogin(bolha.getUsuarioCriacao().getLogin())).thenReturn(bolha.getUsuarioCriacao());
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN, bolha.getUsuarioCriacao().getLogin()))
											     .andExpect(status().isOk())
											     .andExpect(jsonPath("$[0].id", Matchers.is(bolha.getId().intValue())))
											     .andExpect(jsonPath("$[0].nome", Matchers.is(bolha.getNome())))
											     .andExpect(jsonPath("$[0].descricao", Matchers.is(bolha.getDescricao())))
											     .andExpect(jsonPath("$[0].usuarioCriacao.login", Matchers.is(bolha.getUsuarioCriacao().getLogin())))
											     .andExpect(jsonPath("$[0]tipo.nome", Matchers.is(bolha.getTipo().getNome())))
											     .andExpect(jsonPath("$[0].dtHoraCriacao", Matchers.is(bolha.getDtHoraCriacao())))
											     .andExpect(jsonPath("$[0].latitude", Matchers.hasToString(bolha.getLatitude().toString())))
											     .andExpect(jsonPath("$[0].longitude", Matchers.hasToString(bolha.getLongitude().toString())))
											     .andExpect(jsonPath("$[0].indice", Matchers.hasToString(bolha.getIndice().toString())))
											     .andExpect(jsonPath("$[0].indRestrita", Matchers.is(bolha.getIndRestrita())));

		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolha.getUsuarioCriacao().getLogin());
		Mockito.verify(bolhaService, Mockito.times(1)).findBolhaByUser(bolha.getUsuarioCriacao());
		Mockito.verifyNoMoreInteractions(bolhaService);
		Mockito.verifyNoMoreInteractions(usuarioService);		
	}
	
	@Test
	public void getBolhaByUserInvalidLogin() throws Exception{
		String login = "LoginTest";
		Mockito.when(usuarioService.findByLogin(login)).thenReturn(null);
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN, login)).andExpect(status().isFailedDependency());

		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(login);
	}
	
	@Test
	public void getBolhaByUserNoContent() throws Exception{
		
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		List<Bolha> bolhas = new ArrayList<Bolha>(); 
		
		Mockito.when(usuarioService.findByLogin(bolha.getUsuarioCriacao().getLogin())).thenReturn(bolha.getUsuarioCriacao());
		Mockito.when(bolhaService.findBolhaByUser(bolha.getUsuarioCriacao())).thenReturn(bolhas);

		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN, bolha.getUsuarioCriacao().getLogin()))
	     										 .andExpect(status().isNoContent());
		
		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolha.getUsuarioCriacao().getLogin());
		Mockito.verify(bolhaService, Mockito.times(1)).findBolhaByUser(bolha.getUsuarioCriacao());
		Mockito.verifyNoMoreInteractions(bolhaService);
		Mockito.verifyNoMoreInteractions(usuarioService);		
	}
	
/*	@Test
	public void getCloserBolhas() throws Exception{
		
		Latitude latitudeTest = new Latitude("-22.222222");
		Longitude longitudeTest = new Longitude("-47.777777");
		
		Mockito.when(bolhaService.findAllCloserBolhas(latitudeTest, longitudeTest)).thenReturn(listaBolhas);
		
		mockMvc.perform(get(BolhaRestURIConstants.GET_BOLHA_IN_RANGE)
												 .param("latitude", latitudeTest.toString())
												 .param("longitude", longitudeTest.toString()) 
												 ).andExpect(status().isOk())
												  .andExpect(jsonPath("$", Matchers.hasSize(3)))
												  .andExpect(jsonPath("$[*].id", Matchers.containsInAnyOrder(listaBolhas.get(0).getId().intValue(),
														 													listaBolhas.get(1).getId().intValue(),
														 													listaBolhas.get(2).getId().intValue())))
												  .andExpect(jsonPath("$[*].nome", Matchers.containsInAnyOrder(listaBolhas.get(0).getNome(),
														 													  listaBolhas.get(1).getNome(),
														 													  listaBolhas.get(2).getNome())));
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAllCloserBolhas(latitudeTest, longitudeTest);
		Mockito.verifyNoMoreInteractions(bolhaService);
		
	}*/

}
