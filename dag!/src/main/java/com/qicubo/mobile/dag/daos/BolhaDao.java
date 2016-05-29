package com.qicubo.mobile.dag.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Component
@Repository
public class BolhaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Bolha> all() {
		return manager.createQuery("select b from Bolha b", Bolha.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Bolha> findBolhaByUsuario(Long id){
		
		Query query = manager.createQuery("select b from Bolha b "
										+ "where id_usuario = :idUsuario");
		
        query.setParameter("idUsuario", id);
        return query.getResultList(); 
	}
	
	@SuppressWarnings("unchecked")
    public List<Bolha> findCloserBolhas(Latitude latitude, Longitude longitude, BigDecimal index ){
	    
	    Query query = manager.createQuery("");
	    
	    query.setParameter("latitude", latitude);
	    query.setParameter("longitude", longitude);
	    query.setParameter("index", index);
	    
	    return query.getResultList();
	}
	
	public void save(Bolha bolha) {
		manager.persist(bolha);
	}

	public Bolha findById(Long id) {
		return manager.find(Bolha.class, id);
	}

	public void remove(Bolha bolha) {
		manager.remove(bolha);
	}

	public void update(Bolha bolha) {
		manager.merge(bolha);
	}

}
