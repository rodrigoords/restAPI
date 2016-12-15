package com.qicubo.mobile.dag.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@RestController
@RequestMapping(value=BolhaRestURIConstants.BASE_URI)
public class BolhaController {

    @Autowired
    private BolhaService bolhaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoService tipoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BolhaDTO>> getAllBolhas() {
    	
    	List<BolhaDTO> bolhasDTO = new ArrayList<>();
    	
        List<Bolha> bolhas = bolhaService.findAll();
        
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        for (Bolha bolha : bolhas) {
        	bolhasDTO.add(bolha.toDTO());
        }
        
        
        return ResponseEntity.status(HttpStatus.OK).body(bolhasDTO);
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
        	bolhasDTO.add(bolha.toDTO());
        }
        
        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }
    
    @CrossOrigin()
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
        	bolhasDTO.add(bolha.toDTO());
        }
        
        return new ResponseEntity<>(bolhasDTO, HttpStatus.OK);
    }
    
    @CrossOrigin()
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createBolha(@RequestBody BolhaDTO bolhaDTO) {
        
        Bolha bolha = Bolha.fromDTO(bolhaDTO);
        
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
        
        //Verifica se existe uma bolha
        bolhaService.bolhaExist(bolha);

        Index indice = new Index(
                bolha.getLatitude().bigDecimalValue().add(bolha.getLongitude().bigDecimalValue()).toString());

        bolha.setIndice(indice);

        bolha = bolhaService.create(bolha);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bolha.getId()).toUri();
        return ResponseEntity.created(uri).build();
        
    }

}
