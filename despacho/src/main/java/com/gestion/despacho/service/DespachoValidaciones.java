package com.gestion.despacho.service;

import org.springframework.stereotype.Service;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.model.Despacho;

@Service
public class DespachoValidaciones {


    public Boolean validarNullVacio(Despacho despacho){
        if (despacho.getFecha() == null) {
            return null;
        }
        if(despacho.getDestino() == null || despacho.getDestino().trim().length() == 0){
            return null;
        }
        if (despacho.getEstado() == null || despacho.getEstado().trim().length() == 0) {
            return null;
        }
        if(despacho.getCantidad() == null || despacho.getCantidad() == 0){
            return null;
        }
        return true;
    }


    public DespachoDTO convertirADTO(Despacho despacho){
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId_despacho(despacho.getId_despacho());
        if (despacho.getFecha() != null) {
            despachoDTO.setFecha(new java.sql.Date(despacho.getFecha().getTime()));
        }
        despachoDTO.setDestino(despacho.getDestino());
        despachoDTO.setEstado(despacho.getEstado());
        despachoDTO.setCantidad(despacho.getCantidad());
        return despachoDTO;
    }

}
