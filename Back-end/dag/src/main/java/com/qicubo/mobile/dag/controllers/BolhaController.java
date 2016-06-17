package com.qicubo.mobile.dag.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.qicubo.mobile.dag.dto.BolhaDTO;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.BolhaService;
import com.qicubo.mobile.dag.services.TipoService;
import com.qicubo.mobile.dag.services.UsuarioService;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@RestController
public class BolhaController {

    @Autowired
    private BolhaService bolhaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoService tipoService;
    
	private ModelMapper mapper = new ModelMapper();

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_ALL_BOLHAS)
    public ResponseEntity<List<BolhaDTO>> getAllBolhas() {
    	
    	List<BolhaDTO> bolhasDTO = new ArrayList<>();
    	
        List<Bolha> bolhas = bolhaService.findAll();
        
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        for (Bolha bolha : bolhas) {
        	bolhasDTO.add(mapper.map(bolha, BolhaDTO.class));
        }

        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_ID)
    public ResponseEntity<BolhaDTO> getBolhaById(@PathVariable("id") Long id) {
        Bolha bolha = bolhaService.findById(id);
        if (bolha == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        BolhaDTO bolhaDTO = new BolhaDTO(); 
        bolhaDTO = mapper.map(bolha, BolhaDTO.class );
        
        return new ResponseEntity<>(bolhaDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_IN_RANGE)
    public ResponseEntity<List<BolhaDTO>> getCloserBolhas(@RequestParam(value = "lat", required = true) String lat,
            										      @RequestParam(value = "longi", required = true) String longi) {
        
        Latitude latitude = new Latitude(lat);
        Longitude longitude = new Longitude(longi);
        
        List<Bolha> bolhas = bolhaService.findAllCloserBolhas(latitude, longitude);

        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<BolhaDTO> bolhasDTO = new ArrayList<>();
        
        for (Bolha bolha : bolhas){
        	bolhasDTO.add(mapper.map(bolha, BolhaDTO.class));
        }
        
        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN)
    public ResponseEntity<List<BolhaDTO>> getBolhaByUser(@PathVariable("login") String login) {

        Usuario user = usuarioService.findByLogin(login);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        
        List<Bolha> bolhas = bolhaService.findBolhaByUser(user);
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<BolhaDTO> bolhasDTO = new ArrayList<>();
        
        for (Bolha bolha : bolhas){
        	bolhasDTO.add(mapper.map(bolha, BolhaDTO.class));
        }
        
        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = BolhaRestURIConstants.CREATE_BOLHA)
    public ResponseEntity<Void> createBolha(@RequestBody BolhaDTO bolhaDTO, UriComponentsBuilder ucBuilder) {
        
        Bolha bolha = new Bolha();
        
        bolha.setDescricao(bolhaDTO.getDescricao());
        bolha.setDtHoraCriacao(bolhaDTO.getDtHoraCriacao());
        bolha.setIndRestrita(new Integer(bolhaDTO.getIndRestrita()));
        bolha.setLatitude(new Latitude(bolhaDTO.getLatitude()));
        bolha.setLongitude(new Longitude(bolhaDTO.getLongitude()));
        bolha.setNome(bolhaDTO.getNome());
        
        
        Tipo tipo = tipoService.findByName(bolhaDTO.getTipoNome());

        if (tipo == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        Usuario usuario = usuarioService.findByLogin(bolhaDTO.getUsuarioCriacaoLogin());

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        bolha.setUsuarioCriacao(usuario);
        bolha.setTipo(tipo);

        if (bolhaService.isBolhaExist(bolha)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Index indice = new Index(
                bolha.getLatitude().bigDecimalValue().subtract(bolha.getLongitude().bigDecimalValue()).toString());

        bolha.setIndice(indice);

        bolhaService.create(bolha);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(BolhaRestURIConstants.CREATE_BOLHA).buildAndExpand(bolha.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
