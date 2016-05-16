package com.qicubo.mobile.dag.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;

@Controller
@RequestMapping("/tipo")
public class TipoController {

	@Autowired
	private TipoDao tipoDao;


	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Tipo> getUser(@PathVariable("id") Integer id) {
		Tipo tipo = tipoDao.findById(id);
		if (tipo == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tipo, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Tipo>> list() {
		
	    List<Tipo> tipo = tipoDao.all();
		
	    if (tipo.isEmpty()){
	        return new ResponseEntity<List<Tipo>>(HttpStatus.NO_CONTENT);
	    }
	    
		return new ResponseEntity<List<Tipo>>(tipo, HttpStatus.OK);
	}
}
