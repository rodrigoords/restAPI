package com.qicubo.mobile.dag.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qicubo.mobile.dag.models.Usuario;

@Component
@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	   public List<Usuario> all()
	   {
	      return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	   }

	   public void save(Usuario user)
	   {
	      manager.persist(user);
	   }

	   public Usuario findById(Long id)
	   {
	      return manager.find(Usuario.class, id);
	   }
	   
	   public Usuario findByLogin(String login) throws Exception{
		   	
		   Query query = manager.createQuery("select u from Usuario u "
		   									+ "where u.login = :login");
		   query.setParameter("login", login);	
		   
	       return (Usuario) query.getSingleResult(); 
	   }
	   
	   public void remove(Usuario user)
	   {
	      manager.remove(user);
	   }

	   public void update(Usuario user)
	   {
	      manager.merge(user);
	   }

}
