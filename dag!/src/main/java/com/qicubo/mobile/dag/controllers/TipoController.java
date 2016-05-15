package com.qicubo.mobile.dag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
			return new ResponseEntity<Tipo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tipo>(tipo, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page) {
		ModelAndView modelAndView = new ModelAndView("tipo/list");
		modelAndView.addObject("tipoList", tipoDao.all());
		return modelAndView;
	}
}
