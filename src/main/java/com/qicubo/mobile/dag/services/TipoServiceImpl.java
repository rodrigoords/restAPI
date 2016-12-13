package com.qicubo.mobile.dag.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;

@Service("tipoService")
public class TipoServiceImpl implements TipoService {
    private static final Logger log = Logger.getLogger(TipoService.class.getName());

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
            log.log(Level.INFO, "No data found in find by name query", e);
            tipo = null;
        } catch (Exception e) {
            log.log(Level.SEVERE, e.toString(), e);
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
            log.log(Level.INFO, "No data found in find by name query", e);
            exists = false;
        } catch (Exception e) {

            log.log(Level.SEVERE, e.toString(), e);
        }
        return exists;
    }

}
