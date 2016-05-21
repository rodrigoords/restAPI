package com.qicubo.mobile.dag.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.TipoService;

@RestController
public class TipoController {

	@Autowired
	private TipoService tipoService;
	
	@RequestMapping(method = RequestMethod.GET, value=TipoRestURIConstants.GET_ALL_TIPOS)
	public ResponseEntity<List<Tipo>> list() {
		
	    List<Tipo> tipo = tipoService.findAll();
		
	    if (tipo.isEmpty()){
	        return new ResponseEntity<List<Tipo>>(HttpStatus.NO_CONTENT);
	    }
	    
		return new ResponseEntity<List<Tipo>>(tipo, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = TipoRestURIConstants.GET_TIPO_BY_ID)
	public ResponseEntity<Tipo> getTipoById(@PathVariable("id") Long id) {
		Tipo tipo = tipoService.findById(id);
		if (tipo == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tipo, HttpStatus.OK);
	}
	
}
