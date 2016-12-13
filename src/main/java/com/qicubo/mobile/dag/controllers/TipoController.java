package com.qicubo.mobile.dag.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qicubo.mobile.dag.dto.TipoDTO;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;

@RestController
public class TipoController {

	@Autowired
	private TipoService tipoService;
	
	private ModelMapper mapper = new ModelMapper();
	
	@RequestMapping(method = RequestMethod.GET, value=TipoRestURIConstants.GET_ALL_TIPOS)
	public ResponseEntity<List<TipoDTO>> list() {
		
	    List<Tipo> tipos = tipoService.findAll();
		
	    if (tipos.isEmpty()){
	        return new ResponseEntity<List<TipoDTO>>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<TipoDTO> tiposDTO = new ArrayList<>();
	    
	    for(Tipo tipo : tipos){
	    	tiposDTO.add(mapper.map(tipo, TipoDTO.class));
	    }
	    
		return new ResponseEntity<List<TipoDTO>>(tiposDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = TipoRestURIConstants.GET_TIPO_BY_NAME)
	public ResponseEntity<TipoDTO> getTipoByName(@PathVariable("name") String name) {
		Tipo tipo = tipoService.findByName(name);
		if (tipo == null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		TipoDTO tipoDTO = mapper.map(tipo, TipoDTO.class);
		
		return new ResponseEntity<>(tipoDTO, HttpStatus.OK);
	}
	
}
