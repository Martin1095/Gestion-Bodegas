package com.gestion.recepcion.service;

import org.springframework.stereotype.Service;

import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.model.Recepcion;

@Service
public class RecepcionValidaciones {

    public Boolean validarNullVacion(Recepcion recepcion){
        if(recepcion.getFecha() == null){
            return false;
        }
        if(recepcion.getCantidad() == null || recepcion.getCantidad() == 0){
            return false;
        }
        return true;
    }

    RecepcionDTO convertirADTO(Recepcion recepcion) {
        RecepcionDTO recepcionDTO = new RecepcionDTO();
        recepcionDTO.setId(recepcion.getId_recepcion());
        recepcionDTO.setFecha(recepcion.getFecha());
        recepcionDTO.setCantidad(recepcion.getCantidad());
        return recepcionDTO;
    }
}
