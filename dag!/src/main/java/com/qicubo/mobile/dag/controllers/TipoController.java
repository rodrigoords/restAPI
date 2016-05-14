package com.qicubo.mobile.dag.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qicubo.mobile.dag.daos.TipoDao;

@Controller
@RequestMapping("/tipo")
public class TipoController {
	
	@Autowired
	private TipoDao tipoDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page) {
		ModelAndView modelAndView = new ModelAndView("tipo/list");
		modelAndView.addObject("paginatedList", tipoDao.paginated(page, 10));
		return modelAndView;
	}
}
