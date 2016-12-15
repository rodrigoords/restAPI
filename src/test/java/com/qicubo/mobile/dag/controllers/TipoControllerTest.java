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

import com.qicubo.mobile.dag.builders.TipoBuilder;
import com.qicubo.mobile.dag.handler.ControllersExceptionHandler;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;
import com.qicubo.mobile.dag.services.exceptions.TipoNaoEncontradoException;
import com.qicubo.mobile.dag.utils.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class TipoControllerTest {
    
    @InjectMocks
    private TipoController tipoController;
    
	@Mock
	private TipoService tipoService;
	
	private MockMvc mockMvc;
	
	private Tipo tipo;
	
	private List<Tipo> listaTipos = new ArrayList<>();

	@Before
	public void setUp() {
				
		mockMvc = MockMvcBuilders.standaloneSetup(tipoController).setControllerAdvice(new ControllersExceptionHandler()).build();

		Tipo tipoA = new TipoBuilder("Balada").build();
		Tipo tipoB = new TipoBuilder("Estudos").build();
		Tipo tipoC = new TipoBuilder("Esporte").build();
		
		tipoA.setId(1L);
		tipoB.setId(2L);
		tipoC.setId(3L);

		listaTipos.addAll(Arrays.asList(tipoA, tipoB, tipoC));

	}

	@Test
	public void getAllTipos() throws Exception {
		Mockito.when(tipoService.findAll()).thenReturn(listaTipos);
		//
		mockMvc.perform(get(TipoRestURIConstants.URI_BASE)).andExpect(status().isOk())
																.andExpect(jsonPath("$", Matchers.hasSize(3)))
																.andExpect(jsonPath("$[*].nome", Matchers.containsInAnyOrder(listaTipos.get(0).getNome(),
																															 listaTipos.get(1).getNome(),
																															 listaTipos.get(2).getNome())))
																.andExpect(jsonPath("$[*].descricao", Matchers.containsInAnyOrder(listaTipos.get(0).getDescricao(),
																																  listaTipos.get(1).getDescricao(),
																																  listaTipos.get(2).getDescricao())));
		//
		Mockito.verify(tipoService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	
	@Test
	public void getAllTiposNoContent() throws Exception{
		Mockito.when(tipoService.findAll()).thenReturn(new ArrayList<Tipo>());
		//
		mockMvc.perform(get(TipoRestURIConstants.URI_BASE)).andExpect(status().isNoContent());
		//
		Mockito.verify(tipoService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	
	@Test
	public void getTipoByName() throws Exception{
		
		tipo = new TipoBuilder("Balada").build();
		tipo.setId(1L);
		
		Mockito.when(tipoService.findByName(tipo.getNome())).thenReturn(tipo);
		
		mockMvc.perform(get(TestUtil.GET_TIPO_BY_NAME, tipo.getNome()))
										        .andExpect(status().isOk())
										        .andExpect(jsonPath("$.nome", Matchers.is(tipo.getNome())))
										        .andExpect(jsonPath("$.descricao", Matchers.is(tipo.getDescricao())));
		
		Mockito.verify(tipoService, Mockito.times(1)).findByName(tipo.getNome());
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	
	@Test
	public void getTipoByNameNoContent() throws Exception{
		
		tipo = new TipoBuilder("Balada").build();
		tipo.setId(1L);
		Mockito.when(tipoService.findByName(tipo.getNome())).thenThrow(new TipoNaoEncontradoException("Tipo não foi encontrado!"));
		
		mockMvc.perform(get(TestUtil.GET_TIPO_BY_NAME, tipo.getNome())).andExpect(status().isNotFound());
		
	}
	

}
