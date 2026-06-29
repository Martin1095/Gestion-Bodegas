package com.gestion.despacho.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.repository.DespachoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoValidaciones despachoValidaciones;

    @Autowired
    private DespachoRepository repo;

    public List<DespachoDTO> obtenerDespachos() {
        List<DespachoDTO> listaDTOs = new ArrayList<>();
        for (Despacho despacho : repo.findAll()) {
            listaDTOs.add(despachoValidaciones.convertirADTO(despacho));
        }
        return listaDTOs;
    }

    public DespachoDTO guardarDespacho(Despacho nuevoDespacho) {
        if(despachoValidaciones.validarNullVacio(nuevoDespacho)){
            Despacho guardado = repo.save(nuevoDespacho);
            return despachoValidaciones.convertirADTO(guardado);
        }
        return null;
    }

    public DespachoDTO buscarDespachoPorId(Integer id_despacho) {
        Despacho despacho = repo.findById(id_despacho)
            .orElseThrow(() -> new RuntimeException("Despacho no encontrado en los archivos"));
        return despachoValidaciones.convertirADTO(despacho);
    }
}
