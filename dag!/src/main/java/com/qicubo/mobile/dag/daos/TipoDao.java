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
		   List<Tipo> list = manager.createQuery("select t from Tipo t", Tipo.class).getResultList();
		   return list;
	   }

	   public void save(Tipo tipo)
	   {
	      manager.persist(tipo);
	   }

	   public Tipo findById(Integer id)
	   {
	      Tipo tipo = manager.find(Tipo.class, id);
	      return tipo;
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
