package com.qicubo.mobile.dag.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;

@Service("tipoService")
public class TipoServiceImpl implements TipoService {
    @Autowired
    private TipoDao tipoDao;

    @Override
    public Tipo findById(Long id) {
        return tipoDao.findById(id);
    }

    @Override
    public Tipo findByName(String name) {
        Tipo tipo = new Tipo();
        try {
            tipo = tipoDao.findByNome(name);
        } catch (NoResultException e) {
            tipo = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipo;
    }

    @Override
    public List<Tipo> findAll() {
        return tipoDao.all();
    }

    @Override
    public boolean isTipoExist(Tipo tipo) {
        boolean exists = true;
        try {
            tipoDao.findByNome(tipo.getNome());
        } catch (NoResultException e) {
            exists = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

}
