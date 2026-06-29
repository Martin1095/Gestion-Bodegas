package com.gestion.proveedor.service;

import org.springframework.stereotype.Service;

import com.gestion.proveedor.DTO.ProveedorDTO;
import com.gestion.proveedor.model.Proveedor;

@Service
public class ProveedorValidaciones {

    public Boolean validarNullVacio(Proveedor proveedor){
        if(proveedor.getNombre() == null || proveedor.getNombre().trim().length() == 0){
            return false;
        }
        if(proveedor.getCorreo() == null || proveedor.getCorreo().trim().length() == 0){
            return false;
        }
        if(proveedor.getTelefono() == null || proveedor.getTelefono().trim().length() == 0){
            return false;
        }
        return true;
    }

    ProveedorDTO convertirADTO(Proveedor proveedor){
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setCorreo(proveedor.getCorreo());
        proveedorDTO.setTelefono(proveedor.getTelefono());
        return proveedorDTO;
    }
}
