package com.gestion.proveedor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.proveedor.DTO.ProveedorDTO;
import com.gestion.proveedor.model.Proveedor;
import com.gestion.proveedor.repository.ProveedorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorValidaciones proveedorValidaciones;
    

    public List<ProveedorDTO> obtenerProveedores(){
        List<ProveedorDTO> listaDTOs = new ArrayList<>();
        for (Proveedor proveedor : proveedorRepository.findAll()) {
            listaDTOs.add(proveedorValidaciones.convertirADTO(proveedor));
        }
        return listaDTOs;
    }


    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public ProveedorDTO obtenerProveedorPorId(Integer id_proveedor){
        Proveedor proveedor = proveedorRepository.findById(id_proveedor)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id_proveedor));
        return proveedorValidaciones.convertirADTO(proveedor);
    }


}
