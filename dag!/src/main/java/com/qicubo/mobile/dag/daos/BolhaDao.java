package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Bolha;


@Repository
public class BolhaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Bolha> all() {
		return manager.createQuery("select b from dag_bolha b", Bolha.class).getResultList();
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
