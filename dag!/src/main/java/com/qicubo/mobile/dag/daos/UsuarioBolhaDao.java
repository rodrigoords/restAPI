package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.PaginatedList;
import com.qicubo.mobile.dag.models.UsuarioBolha;

@Repository
public class UsuarioBolhaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	   public List<UsuarioBolha> all()
	   {
	      return manager.createQuery("select u from dag_usuario_bolha u", UsuarioBolha.class).getResultList();
	   }

	   public void save(UsuarioBolha userBolha)
	   {
	      manager.persist(userBolha);
	   }

	   public UsuarioBolha findById(Integer id)
	   {
	      return manager.find(UsuarioBolha.class, id);
	   }

	   public void remove(UsuarioBolha userBolha)
	   {
	      manager.remove(userBolha);
	   }

	   public PaginatedList paginated(int page, int max)
	   {
	      return new PaginatorQueryHelper().list(manager, UsuarioBolha.class, page, max);
	   }
}
