package com.gestion.bodega.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.repository.BodegaRepository;

@Service

public class BodegaService {

    @Autowired
    private BodegaRepository repo;

    public List<Bodega> listar(){
        return repo.findAll();
    }

    public Bodega guardar(Bodega bodega){
        return repo.save(bodega);
    }

    public Bodega buscar(Integer id){
        return repo.findById(id)
                .orElseThrow();
    }

    public void eliminar(Integer id){
        repo.deleteById(id);
    }

}