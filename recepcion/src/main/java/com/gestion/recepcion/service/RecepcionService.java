package com.gestion.recepcion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.recepcion.DTO.ProveedorExternoDTO;
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
            listaDTOs.add(recepcionValidaciones.convertirARecepcionDTO(recepcion));
        }
        return listaDTOs;
    }



    public Recepcion guardarRecepcion(Recepcion recepcion) {

        if (!recepcionValidaciones.validarNullVacio(recepcion)) {
        throw new RuntimeException("Los datos de la recepción son inválidos.");
        }

        // Validación del proveedor
        ProveedorExternoDTO proveedor = recepcionValidaciones.obtenerProveedor(recepcion.getId_proveedor());

        if (proveedor.getId_proveedor() == 0) {
            throw new RuntimeException("El proveedor no existe.");
        }

        return recepcionRepository.save(recepcion);
    }


}
