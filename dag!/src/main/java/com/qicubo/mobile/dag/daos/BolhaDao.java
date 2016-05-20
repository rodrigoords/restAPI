package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Bolha;


@Repository
public class BolhaDao {

	@PersistenceContext
	private EntityManager manager;
	private Session       session;

	public List<Bolha> all() {
		return manager.createQuery("select b from Bolha b", Bolha.class).getResultList();
	}
	
	public List<Bolha> findBolhaByUsuario(Long idUsuario){
       Criteria criteria = session.createCriteria(Bolha.class); 
        criteria.add(Restrictions.eq("id_usuario", idUsuario));
        return criteria.list(); 
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
