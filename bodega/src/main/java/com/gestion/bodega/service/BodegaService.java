package com.gestion.bodega.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.bodega.DTO.BodegaDTO;
import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.repository.BodegaRepository;

@Service

public class BodegaService {

    @Autowired
    private BodegaRepository repo;

    @Autowired
    private BodegaValidaciones bodegaValidaciones;

    public List<BodegaDTO> obtenerBodegas() {
        List<BodegaDTO> listaDTOs = new ArrayList<>();
        for (Bodega bodega :repo.findAll()) {
            listaDTOs.add(bodegaValidaciones.convertirADTO(bodega));
        }
        return listaDTOs;
    }

    public BodegaDTO guardarBodega(Bodega bodegaNuevo) {
        if (bodegaValidaciones.validarNullVacio(bodegaNuevo)) {
            Bodega guardado = repo.save(bodegaNuevo);
            return bodegaValidaciones.convertirADTO(guardado);
        }
        return null;
    }

    public BodegaDTO buscarBodegaPorId(Integer id_bodega) {
        Bodega bodega = repo.findById(id_bodega)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada en los archivos"));
        return bodegaValidaciones.convertirADTO(bodega);
    }

    public void eliminar(Integer id){
        repo.deleteById(id);
    }

}