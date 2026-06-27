package com.gestion.inventario.service;

import org.springframework.stereotype.Service;

import com.gestion.inventario.DTO.InventarioDTO;
import com.gestion.inventario.model.Inventario;

@Service
public class InventarioValidaciones {

    public Boolean validarNullVacio(Inventario inventario){
        if(inventario.getStockActual() == null){
            return null;
        }
        if(inventario.getStockMinimo() == null || inventario.getStockMinimo() == 0){
            return null;
        }
        if(inventario.getUbicacion() == null || inventario.getUbicacion().trim().length() == 0){
            return null;
        }
        return true;
    }

    public InventarioDTO convertirADTO(Inventario inventario){
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setId_inventario(inventario.getId_inventario());
        inventarioDTO.setStockActual(inventario.getId_inventario());
        inventarioDTO.setStockMinimo(inventario.getStockMinimo());
        inventarioDTO.setUbicacion(inventario.getUbicacion());
        return inventarioDTO;
    }



}
