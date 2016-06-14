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
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.qicubo.mobile.dag.builders.TipoBuilder;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;

@RunWith(MockitoJUnitRunner.class)
public class TipoControllerTest {
    
    @InjectMocks
    private TipoController tipoController;
    
	@Mock
	private TipoService tipoService;
	
	private Tipo tipo;
	
	private MockMvc mockMvc;
	
	private List<Tipo> listaTipos = new ArrayList<>();

	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(tipoController).build();

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
		mockMvc.perform(get(TipoRestURIConstants.GET_ALL_TIPOS)).andExpect(status().isOk())
																.andExpect(jsonPath("$", Matchers.hasSize(3)))
																.andExpect(jsonPath("$[*].id", Matchers.containsInAnyOrder(listaTipos.get(0).getId().intValue(),
																														   listaTipos.get(1).getId().intValue(),
																														   listaTipos.get(2).getId().intValue())))
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
		mockMvc.perform(get(TipoRestURIConstants.GET_ALL_TIPOS)).andExpect(status().isNoContent());
		//
		Mockito.verify(tipoService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	
	@Test
	public void getTipoById() throws Exception{
		
		tipo = new TipoBuilder("Balada").build();
		tipo.setId(1L);
		
		Mockito.when(tipoService.findById(tipo.getId())).thenReturn(tipo);
		
		mockMvc.perform(get(TipoRestURIConstants.GET_TIPO_BY_ID, tipo.getId()))
										        .andExpect(status().isOk())
										        .andExpect(jsonPath("$.id", Matchers.is(tipo.getId().intValue())))
										        .andExpect(jsonPath("$.nome", Matchers.is(tipo.getNome())))
										        .andExpect(jsonPath("$.descricao", Matchers.is(tipo.getDescricao())));
		
		Mockito.verify(tipoService, Mockito.times(1)).findById(tipo.getId());
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	
	@Test
	public void getTipoByIdNoContent() throws Exception{
		
		tipo = new TipoBuilder("Balada").build();
		tipo.setId(1L);
		Mockito.when(tipoService.findById(tipo.getId())).thenReturn(null);
		
		mockMvc.perform(get(TipoRestURIConstants.GET_TIPO_BY_ID, tipo.getId())).andExpect(status().isNoContent());
		
		Mockito.verify(tipoService, Mockito.times(1)).findById(tipo.getId());
		Mockito.verifyNoMoreInteractions(tipoService);
	}
	

}
