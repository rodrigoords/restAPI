package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.PaginatedList;
import com.qicubo.mobile.dag.models.Tipo;

@Repository
public class TipoDao {

	   @PersistenceContext
	   private EntityManager manager;
	   
	   public List<Tipo> all()
	   {
	      return manager.createQuery("select t from dag_tipo t", Tipo.class).getResultList();
	   }

	   public void save(Tipo tipo)
	   {
	      manager.persist(tipo);
	   }

	   public Tipo findById(Integer id)
	   {
	      return manager.find(Tipo.class, id);
	   }

	   public void remove(Tipo tipo)
	   {
	      manager.remove(tipo);
	   }

	   public void update(Tipo tipo)
	   {
	      manager.merge(tipo);
	   }

	   public PaginatedList paginated(int page, int max)
	   {
	      return new PaginatorQueryHelper().list(manager, Tipo.class, page, max);
	   }
}
