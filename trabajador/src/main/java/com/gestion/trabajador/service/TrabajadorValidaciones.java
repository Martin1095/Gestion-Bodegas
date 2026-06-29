package com.gestion.trabajador.service;

import org.springframework.stereotype.Service;

import com.gestion.trabajador.DTO.TrabajadorDTO;
import com.gestion.trabajador.model.Trabajador;

@Service
public class TrabajadorValidaciones {

    public Boolean validarNullVacio(Trabajador trabajador){
        if(trabajador.getNombre() == null || trabajador.getNombre().trim().length() == 0){
            return null;
        }
        if(trabajador.getCargo() == null || trabajador.getCargo().trim().length() == 0){
            return null;
        }
        if(trabajador.getEdad() == null || trabajador.getEdad() == 0){
            return null;
        }
        return true;
    }

     //metodo para convertir un Trabajador a TrabajadorDTO
    TrabajadorDTO convertirADTO(Trabajador trabajador) {
        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();
        trabajadorDTO.setId(trabajador.getId_trabajador());
        trabajadorDTO.setNombre(trabajador.getNombre());
        trabajadorDTO.setCargo(trabajador.getCargo());
        trabajadorDTO.setEdad(trabajador.getEdad());
        return trabajadorDTO;
    }
}
