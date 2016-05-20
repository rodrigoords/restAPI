package com.qicubo.mobile.dag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;

@Service("tipoService")
public class TipoServiceImpl implements TipoService{
	@Autowired
	private TipoDao tipoDao;
	
	@Override
	public Tipo findById(Long id) {
		return tipoDao.findById(id);
	}

	@Override
	public List<Tipo> findByName(String name) {
		return tipoDao.findByNome(name);
	}

	@Override
	public List<Tipo> findAll() {
		return tipoDao.all();
	}

	@Override
	public boolean isTipoExist(Tipo tipo) {
		boolean exists = false;
		if (!tipoDao.findByNome(tipo.getNome()).isEmpty()){
			exists = true;
		}
		return exists;
	}

	
}
