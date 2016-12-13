package com.qicubo.mobile.dag.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.BolhaDao;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Transactional
@Service("bolhaService")
public class BolhaServiceImpl implements BolhaService {
    
    private static final BigDecimal PERIMETRO_BOLHA_KM = new BigDecimal(50);

	@Autowired
	private BolhaDao bolhaDao;

	@Override
	public Bolha findById(Long id) {
		return bolhaDao.findById(id);
	}

	@Override
	public void create(Bolha bolha) {
		bolhaDao.save(bolha);
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
	public boolean isBolhaExist(Bolha bolha) {
		List<Bolha> bolhas = findBolhaByUser(bolha.getUsuarioCriacao());
		return !bolhas.isEmpty();
	}

	@Override
	public List<Bolha> findAllCloserBolhas(Latitude latitude, Longitude longitude) {
		
	    BigDecimal distancia;
	    Index indice = latitude.indexValue().subtract(longitude.indexValue());
	    
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
