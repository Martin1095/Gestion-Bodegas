package com.gestion.recepcion.service;

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
    private RecepcionRepository recepcionRepository;

    public List<RecepcionDTO> obtenerTodos() {
        return recepcionRepository.findAll()
            .stream()
            .map(this::convertirADTO)
            .toList();
    }

    private RecepcionDTO convertirADTO(Recepcion recepcion) {
        RecepcionDTO dto = new RecepcionDTO();
        dto.setId(recepcion.getId_recepcion());
        dto.setFecha(recepcion.getFecha());
        dto.setCantidad(recepcion.getCantidad());
        return dto;
    }

    public Recepcion guardarRecepcion(Recepcion recepcion) {
        return recepcionRepository.save(recepcion);
    }


}
