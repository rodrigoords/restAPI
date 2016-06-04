package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.types.Index;

@Component
@Repository
public class BolhaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Bolha> all() {
		return manager.createQuery("select b from Bolha b", Bolha.class).getResultList();
	}
	
	public List<Bolha> findBolhaByUsuario(Long id){
		
		TypedQuery<Bolha> query = manager.createQuery("select b from Bolha b "
										+ "where id_usuario = :idUsuario", Bolha.class);
		
        query.setParameter("idUsuario", id);
        return query.getResultList(); 
	}
	
	/**
	 * Retorna as bolhas que estão próximas de acordo com o indice informado, 
	 * esse indice é calculado através da latitude e longitude quando a bolha é criada.
	 * @param index
	 * @return
	 */
    public List<Bolha> findCloserBolhas(Index index ){
	    
	    TypedQuery<Bolha> query = manager.createQuery("select b "
	                                    + "from bolha b "
	                                    + "where indice between :indexA and indexB ", Bolha.class);
	    
	    query.setParameter("indexA", index);
	    query.setParameter("indexB", index);
	    
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
