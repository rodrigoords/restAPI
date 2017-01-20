package com.qicubo.mobile.dag.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.BolhaDao;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.services.exceptions.BolhaExistenteException;
import com.qicubo.mobile.dag.services.exceptions.BolhaNaoEncontradaException;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Transactional
@Service("bolhaService")
public class BolhaServiceImpl implements BolhaService {
    
	private static final Logger log = Logger.getLogger(UsuarioService.class.getName());
    private static final BigDecimal PERIMETRO_BOLHA_KM = new BigDecimal(50);

	@Autowired
	private BolhaDao bolhaDao;

	@Override
	public Bolha findById(Long id) {
		Bolha bolha = bolhaDao.findById(id);
		
		if(bolha == null){
        	throw new BolhaNaoEncontradaException("Não foi possível localizar a bolha.");
        }
		return  bolha;
	}

	@Override
	public Bolha create(Bolha bolha) {
		log.info("Criando bolha a partir de uma entidade Bolha");
		return bolhaDao.save(bolha);
	}

	@Override
	public void update(Bolha bolha) {
		bolhaDao.update(bolha);

	}

	@Override
	public void deleteById(Long id) {
		Bolha bolha = bolhaDao.findById(id);
		bolhaDao.remove(bolha);
	}

	@Override
	public List<Bolha> findAll() {
		return bolhaDao.all();
	}
	@Override
	public void bolhaExist(Bolha bolha) {
		List<Bolha> bolhas = findBolhaByUser(bolha.getUsuarioCriacao());
		
        if (!bolhas.isEmpty()) {
            throw new BolhaExistenteException("A bolha a ser criada já existe.");
        }

	}

	@Override
	public List<Bolha> findAllCloserBolhas(Latitude latitude, Longitude longitude) {
		
	    BigDecimal distancia;
	    Index indice = latitude.indexValue().add(longitude.indexValue());
	    
	    List<Bolha> bolhas = new ArrayList<>();
	    
	    for ( Bolha bolha : bolhaDao.findCloserBolhas(indice) ){
	        distancia = bolha.distancia(latitude, longitude, "K");
	        
	        if (distancia.compareTo(PERIMETRO_BOLHA_KM) <= 0 ){
	            bolhas.add(bolha);
	        }
	    }
	    
	    return bolhas;
	}

	@Override
	public List<Bolha> findBolhaByUser(Usuario user) {
		return bolhaDao.findBolhaByUsuario(user.getId());
	}

}
