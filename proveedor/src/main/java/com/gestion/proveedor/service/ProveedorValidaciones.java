package com.gestion.proveedor.service;

import org.springframework.stereotype.Service;

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

    
}
