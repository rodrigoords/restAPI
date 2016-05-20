package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Tipo;

@Repository
public class TipoDao {

    @PersistenceContext
    private EntityManager manager;
    private Session	session;
    
    public List<Tipo> all() {
        return manager.createQuery("select t from Tipo t", Tipo.class).getResultList();
    }

    public void save(Tipo tipo) {
        manager.persist(tipo);
    }

    public Tipo findById(Long id) {
        return manager.find(Tipo.class, id);
    }
    
    @SuppressWarnings("unchecked")
	public List<Tipo> findByNome(String nome){
    	Criteria criteria = session.createCriteria(Tipo.class); 
    	criteria.add(Restrictions.eq("nome", nome));
    	return criteria.list(); 
    }
    
    public void remove(Tipo tipo) {
        manager.remove(tipo);
    }

    public void update(Tipo tipo) {
        manager.merge(tipo);
    }

}
