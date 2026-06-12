package com.gestion.despacho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.repository.DespachoRepository;

@Service
public class DespachoService {

    @Autowired
    private DespachoRepository repo;

    public List<Despacho> listar(){
        return repo.findAll();
    }

    public Despacho guardar(Despacho despacho){
        return repo.save(despacho);
    }

    public Despacho buscar(Integer id){
        return repo.findById(id).orElseThrow();
    }
}
