package com.qicubo.mobile.dag.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.BolhaDao;
import com.qicubo.mobile.dag.models.Bolha;
import com.qicubo.mobile.dag.models.Usuario;

@Service("bolhaService")
public class BolhaServiceImpl implements BolhaService {

	@Autowired
	private BolhaDao bolhaDao;

	@Override
	public Bolha findById(Long id) {
		return bolhaDao.findById(id);
	}

	@Override
	public void create(Bolha bolha) {
		bolhaDao.save(bolha);
	}

	@Override
	public void update(Bolha bolha) {
		bolhaDao.update(bolha);

	}

	@Override
	public void deleteById(Long id) {
		Bolha bolha = bolhaDao.findById(id);
		bolhaDao.remove(bolha);
	}

	@Override
	public List<Bolha> findAll() {
		return bolhaDao.all();
	}

	@Override
	public boolean isBolhaExist(Bolha bolha) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Bolha> findAllCloserBolhas(Long latitude, Long longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bolha> findBolhaByUser(Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}

}
