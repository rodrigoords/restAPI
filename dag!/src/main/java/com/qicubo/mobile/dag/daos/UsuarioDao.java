package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.PaginatedList;
import com.qicubo.mobile.dag.models.Usuario;

@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	   public List<Usuario> all()
	   {
	      return manager.createQuery("select u from dag_usuario u", Usuario.class).getResultList();
	   }

	   public void save(Usuario user)
	   {
	      manager.persist(user);
	   }

	   public Usuario findById(Integer id)
	   {
	      return manager.find(Usuario.class, id);
	   }

	   public void remove(Usuario user)
	   {
	      manager.remove(user);
	   }

	   public void update(Usuario user)
	   {
	      manager.merge(user);
	   }

	   public PaginatedList paginated(int page, int max)
	   {
	      return new PaginatorQueryHelper().list(manager, Usuario.class, page, max);
	   }
}
