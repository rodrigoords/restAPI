package com.qicubo.mobile.dag.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.qicubo.mobile.dag.Boot;
import com.qicubo.mobile.dag.builders.TipoBuilder;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Boot.class)
@WebAppConfiguration
public class TipoControllerTest {


	@Autowired
	private WebApplicationContext context;

	@Mock
	private TipoService tipoService;
	
	private MockMvc mockMvc;
	
	private List<Tipo> listaTipos = new ArrayList<>();

	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		Tipo tipoA = new TipoBuilder("Balada").build();
		Tipo tipoB = new TipoBuilder("Estudos").build();
		Tipo tipoC = new TipoBuilder("Esporte").build();

		listaTipos.addAll(Arrays.asList(tipoA, tipoB, tipoC));

	}

	@Test
	public void findAllTipos() throws Exception {
		Mockito.when(tipoService.findAll()).thenReturn(listaTipos);
		//
		mockMvc.perform(get(TipoRestURIConstants.GET_ALL_TIPOS)).andExpect(status().isOk());
		//
		Mockito.verify(tipoService, Mockito.times(1)).findAll();
		Mockito.verifyNoMoreInteractions(tipoService);
	}

}
