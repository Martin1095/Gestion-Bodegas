package com.gestion.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.inventario.model.Inventario;
import com.gestion.inventario.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository repo;

    public List<Inventario> listar(){
        return repo.findAll();
    }

    public Inventario guardar(Inventario inv){
        return repo.save(inv);

    }

    public Inventario buscar(Integer id){
        return repo.findById(id).orElseThrow();
    }

}
