package com.gestion.bodega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.bodega.DTO.BodegaDTO;
import com.gestion.bodega.model.Bodega;

@Service
public class BodegaValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(Bodega bodega){
        if(bodega.getNombre() == null || bodega.getNombre().trim().length() == 0){
            return null;
        }
        if(bodega.getDireccion() == null || bodega.getDireccion().trim().length() == 0){
            return null;
        }
        return true;
    }

    public BodegaDTO convertirADTO(Bodega bodega){
        BodegaDTO bodegaDTO = new BodegaDTO();
        bodegaDTO.setNombre(bodega.getNombre());
        bodegaDTO.setDireccion(bodega.getDireccion());
        return bodegaDTO;
    }


}
