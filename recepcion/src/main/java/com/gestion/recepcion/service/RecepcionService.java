package com.gestion.recepcion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.model.Recepcion;
import com.gestion.recepcion.repository.RecepcionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RecepcionService {

    @Autowired
    private RecepcionValidaciones recepcionValidaciones;

    @Autowired
    private RecepcionRepository recepcionRepository;

    public List<RecepcionDTO> obtenerRecepciones(){
        List<RecepcionDTO> listaDTOs = new ArrayList<>();
        for (Recepcion recepcion: recepcionRepository.findAll()) {
            listaDTOs.add(recepcionValidaciones.convertirADTO(recepcion));
        }
        return listaDTOs;
    }



    public Recepcion guardarRecepcion(Recepcion recepcion) {
        return recepcionRepository.save(recepcion);
    }


}
