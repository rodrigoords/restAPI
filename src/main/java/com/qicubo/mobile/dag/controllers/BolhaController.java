package com.qicubo.mobile.dag.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.qicubo.mobile.dag.dto.BolhaDTO;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.BolhaService;
import com.qicubo.mobile.dag.services.TipoService;
import com.qicubo.mobile.dag.services.UsuarioService;
import com.qicubo.mobile.dag.services.exceptions.TipoBolhaNullException;
import com.qicubo.mobile.dag.services.exceptions.TipoNaoEncontradoException;
import com.qicubo.mobile.dag.services.exceptions.UsuarioBolhaNullException;
import com.qicubo.mobile.dag.services.exceptions.UsuarioNaoEncontradoException;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@RestController
@RequestMapping(value=BolhaRestURIConstants.BASE_URI)
public class BolhaController {
	
	private static final Logger log = Logger.getLogger(UsuarioService.class.getName());
	
    @Autowired
    private BolhaService bolhaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoService tipoService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Link>> getAllBolhas() {
    	
        List<Bolha> bolhas = bolhaService.findAll();
        
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<Link> bolhaLinks = bolhas.stream()
        	.map(bolha -> ControllerLinkBuilder.linkTo(BolhaController.class).slash(bolha).withSelfRel())
        	.collect(Collectors.toList());
        
        return ResponseEntity.status(HttpStatus.OK).body(bolhaLinks);
    }
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_ID)
    public ResponseEntity<BolhaDTO> getBolhaById(@PathVariable("id") Long id) {
        Bolha bolha = bolhaService.findById(id);
        
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(bolha.toDTO());
    }
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_IN_RANGE)
    public ResponseEntity<List<Link>> getCloserBolhas(@RequestParam(value = "lat", required = true) String lat,
            										      @RequestParam(value = "longi", required = true) String longi) {
    	
    	List<Link> bolhaLinks = new ArrayList<>();
    	
        Latitude latitude = new Latitude(lat);
        Longitude longitude = new Longitude(longi);
        
        List<Bolha> bolhas = bolhaService.findAllCloserBolhas(latitude, longitude);

        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        for (Bolha bolha : bolhas){
        	
        	Link link = ControllerLinkBuilder.linkTo(BolhaController.class).slash(bolha).withSelfRel();
        	
        	bolhaLinks.add(link);
        }
        
        return new ResponseEntity<>(bolhaLinks, HttpStatus.OK);
    }
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN)
    public ResponseEntity<List<BolhaDTO>> getBolhaByUser(@PathVariable("login") String login) {
    	
    	Usuario user = new Usuario();
    	
    	try {
    		user = usuarioService.findByLogin(login);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioBolhaNullException("Usuario inválido!");
		}
        
        List<Bolha> bolhas = bolhaService.findBolhaByUser(user);
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
		List<BolhaDTO> bolhasDTO = bolhas.stream()
							        	 .map(Bolha::toDTO)
							        	 .collect(Collectors.toList());
        
        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createBolha(@RequestBody BolhaDTO bolhaDTO) {
    	
    	Bolha bolha = Bolha.fromDTO(bolhaDTO);
    	Tipo tipo = new Tipo();
    	Usuario usuario = new Usuario();
    	
    	try {			
    		tipo = tipoService.findByName(bolhaDTO.getTipoNome());
		} catch (TipoNaoEncontradoException e) {
			throw new TipoBolhaNullException("Tipo da bolha deve ser valido!");
		}
    	
    	try {
    		usuario = usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin());
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioBolhaNullException("Usuario de criacao da bolha deve ser valido!");
		}
    	
        bolha.setUsuarioCriacao(usuario);
        bolha.setTipo(tipo);
        
        //Verifica se existe uma bolha
        bolhaService.bolhaExist(bolha);
        
        log.info("Criando Indice da bolha.");
        Index indice = new Index(
                bolha.getLatitude().bigDecimalValue().add(bolha.getLongitude().bigDecimalValue()).toString());

        bolha.setIndice(indice);
    	
        bolha = bolhaService.create(bolha);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bolha.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }

}
