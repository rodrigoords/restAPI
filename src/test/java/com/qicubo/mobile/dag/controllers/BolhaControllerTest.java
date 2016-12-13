package com.qicubo.mobile.dag.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qicubo.mobile.dag.builders.BolhaBuilder;
import com.qicubo.mobile.dag.dto.BolhaDTO;
import com.qicubo.mobile.dag.handler.ControllersExceptionHandler;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.services.BolhaService;
import com.qicubo.mobile.dag.services.TipoService;
import com.qicubo.mobile.dag.services.UsuarioService;
import com.qicubo.mobile.dag.services.exceptions.BolhaExistenteException;
import com.qicubo.mobile.dag.services.exceptions.BolhaNaoEncontradaException;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;
import com.qicubo.mobile.dag.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class BolhaControllerTest {
	
	@InjectMocks
	private BolhaController bolhaController;
	
	@Mock
	private BolhaService bolhaService;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private TipoService tipoService;
	
	@Mock
	private BolhaDTO bolhaDTO;
	
	private MockMvc mockMvc;
	
	private List<Bolha> listaBolhas = new ArrayList<>();
	
	private ModelMapper mapper = new ModelMapper();
	
	@Before
	public void setUp(){
		//Crio o mockMvc para controlar os testes, e seto o Controller Advice para controlar as exceptions
		mockMvc = MockMvcBuilders.standaloneSetup(bolhaController)
								 .setControllerAdvice(new ControllersExceptionHandler())
								 .build();
		
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
		
		mockMvc.perform(get(TestUtil.GET_ALL_BOLHAS_URI))
												 .andExpect(status().isOk())
												 .andExpect(jsonPath("$", Matchers.hasSize(3)))
												 .andExpect(jsonPath("$[*].usuarioCriacaoLogin", Matchers.containsInAnyOrder(listaBolhas.get(0).getUsuarioCriacao().getLogin(),
														 																	 listaBolhas.get(1).getUsuarioCriacao().getLogin(),
														 																	 listaBolhas.get(2).getUsuarioCriacao().getLogin())))
												 .andExpect(jsonPath("$[*].nome", Matchers.containsInAnyOrder(listaBolhas.get(0).getNome(),
														 													  listaBolhas.get(1).getNome(),
														 													  listaBolhas.get(2).getNome())));
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void getAllBolhasNoContent() throws Exception{
		Mockito.when(bolhaService.findAll()).thenReturn(new ArrayList<Bolha>());
		
		mockMvc.perform(get(TestUtil.GET_ALL_BOLHAS_URI)).andExpect(status().isNoContent());
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void getBolhaById() throws Exception{
		
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		
		Mockito.when(bolhaService.findById(bolha.getId())).thenReturn(bolha);
		
		mockMvc.perform(get(TestUtil.GET_BOLHAS_BY_ID_URI, bolha.getId()))
											     .andExpect(status().isOk())
											     .andExpect(jsonPath("$.nome", Matchers.is(bolha.getNome())))
											     .andExpect(jsonPath("$.descricao", Matchers.is(bolha.getDescricao())))
											     .andExpect(jsonPath("$.usuarioCriacaoLogin", Matchers.is(bolha.getUsuarioCriacao().getLogin())))
											     .andExpect(jsonPath("$.tipoNome", Matchers.is(bolha.getTipo().getNome())))
											     .andExpect(jsonPath("$.dtHoraCriacao", Matchers.is(bolha.getDtHoraCriacao())))
											     .andExpect(jsonPath("$.latitude", Matchers.hasToString(bolha.getLatitude().toString())))
											     .andExpect(jsonPath("$.longitude", Matchers.hasToString(bolha.getLongitude().toString())))
											     .andExpect(jsonPath("$.indRestrita", Matchers.is(bolha.getIndRestrita().toString())));

		Mockito.verify(bolhaService, Mockito.times(1)).findById(bolha.getId());
		Mockito.verifyNoMoreInteractions(bolhaService);		
	}
	
	@Test
	public void getBolhaByIdNotFound() throws Exception{
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		
	    Mockito.when(bolhaService.findById(bolha.getId())).thenThrow(new BolhaNaoEncontradaException("Não foi possível localizar a bolha."));
	    
		mockMvc.perform(get(TestUtil.GET_BOLHAS_BY_ID_URI, bolha.getId()))
											     .andExpect(status().isNotFound());
											     
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
		
		mockMvc.perform(get(TestUtil.GET_BOLHAS_BY_USER_LOGIN_URI, bolha.getUsuarioCriacao().getLogin()))
											     .andExpect(status().isOk())
											     .andExpect(jsonPath("$[0].nome", Matchers.is(bolha.getNome())))
											     .andExpect(jsonPath("$[0].descricao", Matchers.is(bolha.getDescricao())))
											     .andExpect(jsonPath("$[0].usuarioCriacaoLogin", Matchers.is(bolha.getUsuarioCriacao().getLogin())))
											     .andExpect(jsonPath("$[0].tipoNome", Matchers.is(bolha.getTipo().getNome())))
											     .andExpect(jsonPath("$[0].dtHoraCriacao", Matchers.is(bolha.getDtHoraCriacao())))
											     .andExpect(jsonPath("$[0].latitude", Matchers.hasToString(bolha.getLatitude().toString())))
											     .andExpect(jsonPath("$[0].longitude", Matchers.hasToString(bolha.getLongitude().toString())))
											     .andExpect(jsonPath("$[0].indRestrita", Matchers.is(bolha.getIndRestrita().toString())));

		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolha.getUsuarioCriacao().getLogin());
		Mockito.verify(bolhaService, Mockito.times(1)).findBolhaByUser(bolha.getUsuarioCriacao());
		Mockito.verifyNoMoreInteractions(bolhaService);
		Mockito.verifyNoMoreInteractions(usuarioService);		
	}
	
	@Test
	public void getBolhaByUserInvalidLogin() throws Exception{
		String login = "LoginTest";
		Mockito.when(usuarioService.findByLogin(login)).thenReturn(null);
		
		mockMvc.perform(get(TestUtil.GET_BOLHAS_BY_USER_LOGIN_URI, login)).andExpect(status().isFailedDependency());

		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(login);
	}
	
	@Test
	public void getBolhaByUserNoContent() throws Exception{
		
		Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
		bolha.setId(1L);
		List<Bolha> bolhas = new ArrayList<Bolha>(); 
		
		Mockito.when(usuarioService.findByLogin(bolha.getUsuarioCriacao().getLogin())).thenReturn(bolha.getUsuarioCriacao());
		Mockito.when(bolhaService.findBolhaByUser(bolha.getUsuarioCriacao())).thenReturn(bolhas);

		mockMvc.perform(get(TestUtil.GET_BOLHAS_BY_USER_LOGIN_URI, bolha.getUsuarioCriacao().getLogin()))
	     										 .andExpect(status().isNoContent());
		
		Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolha.getUsuarioCriacao().getLogin());
		Mockito.verify(bolhaService, Mockito.times(1)).findBolhaByUser(bolha.getUsuarioCriacao());
		Mockito.verifyNoMoreInteractions(bolhaService);
		Mockito.verifyNoMoreInteractions(usuarioService);		
	}
	
	@Test
	public void getCloserBolhas() throws Exception{
		
		Latitude latitudeTest = new Latitude("-22.222222");
		Longitude longitudeTest = new Longitude("-47.777777");
		
		Mockito.when(bolhaService.findAllCloserBolhas(latitudeTest, longitudeTest)).thenReturn(listaBolhas);
		
		mockMvc.perform(get(TestUtil.GET_BOLHAS_IN_RANGE_URI)
												 .param("lat", latitudeTest.toString())
												 .param("longi", longitudeTest.toString()))
		                                         .andExpect(status().isOk())
												 .andExpect(jsonPath("$", Matchers.hasSize(3)))
												 .andExpect(status().isOk())
												 .andExpect(jsonPath("$", Matchers.hasSize(3)))
												 .andExpect(jsonPath("$[*].usuarioCriacaoLogin", Matchers.containsInAnyOrder(listaBolhas.get(0).getUsuarioCriacao().getLogin(),
														 																	 listaBolhas.get(1).getUsuarioCriacao().getLogin(),
														 																	 listaBolhas.get(2).getUsuarioCriacao().getLogin())))
												 .andExpect(jsonPath("$[*].nome", Matchers.containsInAnyOrder(listaBolhas.get(0).getNome(),
														 													  listaBolhas.get(1).getNome(),
														 													  listaBolhas.get(2).getNome())));
		
		Mockito.verify(bolhaService, Mockito.times(1)).findAllCloserBolhas(latitudeTest, longitudeTest);
		Mockito.verifyNoMoreInteractions(bolhaService);
		
	}
	
	@Test
	public void getcloserBolhasNoContent() throws Exception{
	    
        Latitude latitudeTest = new Latitude("-22.222222");
        Longitude longitudeTest = new Longitude("-47.777777");
        
        Mockito.when(bolhaService.findAllCloserBolhas(latitudeTest, longitudeTest)).thenReturn(new ArrayList<Bolha>());
        
        mockMvc.perform(get(TestUtil.GET_BOLHAS_IN_RANGE_URI)
                .param("lat", latitudeTest.toString())
                .param("longi", longitudeTest.toString()))
                .andExpect(status().isNoContent());
        
        Mockito.verify(bolhaService, Mockito.times(1)).findAllCloserBolhas(latitudeTest, longitudeTest);
        Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void createBolha() throws Exception{
		
		ModelMapper mapper = new ModelMapper();
		
	    Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
	    bolha.setId(1L);
	    
	    String location = TestUtil.BASE_URL + TestUtil.BASE_URI + "/" + bolha.getId();
	    
	    bolhaDTO = mapper.map(bolha, BolhaDTO.class);
	    
	    Mockito.when(tipoService.findByName(bolhaDTO.getTipoNome())).thenReturn(bolha.getTipo());
	    Mockito.when(usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin())).thenReturn(bolha.getUsuarioCriacao());
	    Mockito.when(bolhaService.create(bolha)).thenReturn(bolha);
	    
        mockMvc.perform(post(TestUtil.CREATE_BOLHA_URI).contentType(TestUtil.APPLICATION_JSON_UTF8)
        														.content(TestUtil.convertObjectToJsonBytes(bolhaDTO)))
                                                  				.andExpect(status().isCreated())
                                                  				.andExpect(header().string("Location", location));
        
        Mockito.verify(tipoService, Mockito.times(1)).findByName(bolhaDTO.getTipoNome());
        Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolhaDTO.getUsuarioCriacaoLogin());
        Mockito.verify(bolhaService, Mockito.times(1)).bolhaExist(bolha);
        Mockito.verify(bolhaService, Mockito.times(1)).create(bolha);
        Mockito.verifyNoMoreInteractions(tipoService);
        Mockito.verifyNoMoreInteractions(usuarioService);
        Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void createBolhaWithoutTipo() throws Exception{
		
	    Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
	    bolha.setTipo(null);
	    
	    bolhaDTO = mapper.map(bolha, BolhaDTO.class);
	    
	    Mockito.when(tipoService.findByName(bolhaDTO.getTipoNome())).thenReturn(bolha.getTipo());
	    Mockito.when(usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin())).thenReturn(bolha.getUsuarioCriacao());
	    
	    
        mockMvc.perform(post(TestUtil.CREATE_BOLHA_URI).contentType(TestUtil.APPLICATION_JSON_UTF8)
        														.content(TestUtil.convertObjectToJsonBytes(bolhaDTO)))
                                                  				.andExpect(status().isFailedDependency());
        
        Mockito.verify(tipoService, Mockito.times(1)).findByName(bolhaDTO.getTipoNome());
        Mockito.verify(usuarioService, Mockito.times(0)).findByLogin(bolhaDTO.getUsuarioCriacaoLogin());
        Mockito.verify(bolhaService, Mockito.times(0)).bolhaExist(bolha);
        Mockito.verify(bolhaService, Mockito.times(0)).create(bolha);
        Mockito.verifyNoMoreInteractions(tipoService);
        Mockito.verifyNoMoreInteractions(usuarioService);
        Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void createBolhaWithoutUserLogin() throws Exception{
		
	    Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
	    bolha.setUsuarioCriacao(null);
	    
	    bolhaDTO = mapper.map(bolha, BolhaDTO.class);
	    
	    Mockito.when(tipoService.findByName(bolhaDTO.getTipoNome())).thenReturn(bolha.getTipo());
	    Mockito.when(usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin())).thenReturn(bolha.getUsuarioCriacao());
	    
	    
        mockMvc.perform(post(TestUtil.CREATE_BOLHA_URI).contentType(TestUtil.APPLICATION_JSON_UTF8)
        														.content(TestUtil.convertObjectToJsonBytes(bolhaDTO)))
                                                  				.andExpect(status().isFailedDependency());
        
        Mockito.verify(tipoService, Mockito.times(1)).findByName(bolhaDTO.getTipoNome());
        Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolhaDTO.getUsuarioCriacaoLogin());
        Mockito.verify(bolhaService, Mockito.times(0)).bolhaExist(bolha);
        Mockito.verify(bolhaService, Mockito.times(0)).create(bolha);
        Mockito.verifyNoMoreInteractions(tipoService);
        Mockito.verifyNoMoreInteractions(usuarioService);
        Mockito.verifyNoMoreInteractions(bolhaService);
	}
	
	@Test
	public void CreateBolhaExists() throws Exception{
		
	    Bolha bolha = new BolhaBuilder("Bolha Teste API").build();
	    
	    bolhaDTO = mapper.map(bolha, BolhaDTO.class);
	    
	    Mockito.when(tipoService.findByName(bolhaDTO.getTipoNome())).thenReturn(bolha.getTipo());
	    Mockito.when(usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin())).thenReturn(bolha.getUsuarioCriacao());
	    Mockito.doThrow(new BolhaExistenteException("A bolha a ser criada já existe.")).when(bolhaService).bolhaExist(bolha);
	    
        mockMvc.perform(post(TestUtil.CREATE_BOLHA_URI).contentType(TestUtil.APPLICATION_JSON_UTF8)
        														.content(TestUtil.convertObjectToJsonBytes(bolhaDTO)))
                                                  				.andExpect(status().isConflict());
        
        Mockito.verify(tipoService, Mockito.times(1)).findByName(bolhaDTO.getTipoNome());
        Mockito.verify(usuarioService, Mockito.times(1)).findByLogin(bolhaDTO.getUsuarioCriacaoLogin());
        Mockito.verify(bolhaService, Mockito.times(1)).bolhaExist(bolha);
        Mockito.verify(bolhaService, Mockito.times(0)).create(bolha);
        Mockito.verifyNoMoreInteractions(tipoService);
        Mockito.verifyNoMoreInteractions(usuarioService);
        Mockito.verifyNoMoreInteractions(bolhaService);
	}

}
