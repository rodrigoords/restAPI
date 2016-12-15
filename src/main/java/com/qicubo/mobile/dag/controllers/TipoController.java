package com.qicubo.mobile.dag.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qicubo.mobile.dag.dto.TipoDTO;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;

@RestController
@RequestMapping(value=TipoRestURIConstants.URI_BASE)
public class TipoController {

	@Autowired
	private TipoService tipoService;
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipoDTO>> list() {
		
	    List<Tipo> tipos = tipoService.findAll();
		
	    if (tipos.isEmpty()){
	        return new ResponseEntity<List<TipoDTO>>(HttpStatus.NO_CONTENT);
	    }
	    
	    List<TipoDTO> tiposDTO = new ArrayList<>();
	    
	    for(Tipo tipo : tipos){
	    	tiposDTO.add(tipo.toDTO());
	    }
	    
		return new ResponseEntity<List<TipoDTO>>(tiposDTO, HttpStatus.OK);
	}
	
	@CrossOrigin()
	@RequestMapping(method = RequestMethod.GET, value = TipoRestURIConstants.GET_TIPO_BY_NAME)
	public ResponseEntity<TipoDTO> getTipoByName(@PathVariable("name") String name) {
		Tipo tipo = tipoService.findByName(name);

		return new ResponseEntity<>(tipo.toDTO(), HttpStatus.OK);
	}
	
}
