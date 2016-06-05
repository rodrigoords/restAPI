package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Tipo;

@Component
@Repository
public class TipoDao {

    @PersistenceContext
    private EntityManager manager;
    
    public List<Tipo> all() {
        return manager.createQuery("select t from Tipo t", Tipo.class).getResultList();
    }

    public void save(Tipo tipo) {
        manager.persist(tipo);
    }

    public Tipo findById(Long id) {
        return manager.find(Tipo.class, id);
    }
       
	public Tipo findByNome(String nome) throws NoResultException{
		
		TypedQuery<Tipo> query = manager.createQuery("select t from Tipo t "
										+ "where t.nome = :nome", Tipo.class);
		query.setParameter("nome", nome);
    	
    	return query.getSingleResult(); 
    }
    
    public void remove(Tipo tipo) {
        manager.remove(tipo);
    }

    public void update(Tipo tipo) {
        manager.merge(tipo);
    }

}
