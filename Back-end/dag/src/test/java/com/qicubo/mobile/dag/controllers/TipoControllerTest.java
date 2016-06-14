package com.qicubo.mobile.dag.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

		listaTipos.addAll(Arrays.asList(tipoA, tipoB, tipoC));

	}

	@Test
	public void getAllTipos() throws Exception {
		Mockito.when(tipoService.findAll()).thenReturn(listaTipos);
		//
		mockMvc.perform(get(TipoRestURIConstants.GET_ALL_TIPOS)).andExpect(status().isOk());
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
		
		mockMvc.perform(get(TipoRestURIConstants.GET_TIPO_BY_ID, tipo.getId())).andExpect(status().isOk());
		
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
