package com.qicubo.mobile.dag.controllers;

import java.util.List;

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
    @Autowired
    private Latitude latitude;
    @Autowired
    private Longitude longitude;

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_ALL_BOLHAS)
    public ResponseEntity<List<Bolha>> getAllBolhas() {

        List<Bolha> bolhas = bolhaService.findAll();

        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bolhas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_ID)
    public ResponseEntity<Bolha> getBolhaById(@PathVariable("id") Long id) {
        Bolha bolha = bolhaService.findById(id);
        if (bolha == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bolha, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_IN_RANGE)
    public ResponseEntity<List<Bolha>> getCloserBolhas(@RequestParam(value = "lat", required = true) String lat,
            										   @RequestParam(value = "longi", required = true) String longi) {
        
        List<Bolha> bolhas = bolhaService.findAllCloserBolhas(latitude.newLatitude(lat), longitude.newLongitude(longi));

        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bolhas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN)
    public ResponseEntity<List<Bolha>> getBolhaByUser(@PathVariable("login") String login) {

        Usuario user = usuarioService.findByLogin(login);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        List<Bolha> bolhas = bolhaService.findBolhaByUser(user);
        if (bolhas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bolhas, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = BolhaRestURIConstants.CREATE_BOLHA)
    public ResponseEntity<Void> createBolha(@RequestBody Bolha bolha, UriComponentsBuilder ucBuilder) {

        Tipo tipo = tipoService.findByName(bolha.getTipo().getNome());

        if (tipo == null) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

        Usuario usuario = usuarioService.findByLogin(bolha.getUsuarioCriacao().getLogin());

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
