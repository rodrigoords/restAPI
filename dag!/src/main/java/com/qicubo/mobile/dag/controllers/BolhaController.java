package com.qicubo.mobile.dag.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.BolhaService;

@Controller
@RequestMapping("/bolha")
public class BolhaController {
	
	@Autowired
	private BolhaService bolhaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Bolha>> list() {
		
	    List<Bolha> bolhas = bolhaService.findAllBolhas();
		
	    if (bolhas.isEmpty()){
	        return new ResponseEntity<List<Bolha>>(HttpStatus.NO_CONTENT);
	    }
	    
		return new ResponseEntity<List<Bolha>>(bolhas, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Bolha> getBolhaById(@PathVariable("id") Long id) {
		Bolha bolha = bolhaService.findById(id);
		if (bolha == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(bolha, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{user}")
	public ResponseEntity<List<Bolha>>  getBolhaByUser(@PathVariable("user") Usuario user) {
		List<Bolha> bolhas = bolhaService.findBolhaByUser(user);
		if (bolhas.isEmpty()){
			return new ResponseEntity<List<Bolha>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Bolha>>(bolhas, HttpStatus.OK);
	}
	
}
