package com.qicubo.mobile.dag.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qicubo.mobile.dag.daos.TipoDao;
import com.qicubo.mobile.dag.models.Tipo;
import com.qicubo.mobile.dag.services.exceptions.TipoNaoEncontradoException;

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
        tipo = tipoDao.findByNome(name);
        if (tipo == null){
        	throw new TipoNaoEncontradoException("O tipo não foi encontrado!");
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

        tipo = tipoDao.findByNome(tipo.getNome());
        if (tipo == null){
        	exists = false;
        	log.log(Level.INFO, "Tipo não foi encontrado.");
        }
        return exists;
    }

}
