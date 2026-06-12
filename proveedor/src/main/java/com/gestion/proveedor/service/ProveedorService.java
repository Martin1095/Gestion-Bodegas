package com.gestion.proveedor.service;

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
    
    private ProveedorDTO convertirADTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId_proveedor(proveedor.getId_proveedor());
        dto.setNombre(proveedor.getNombre());
        dto.setCorreo(proveedor.getCorreo());
        dto.setTelefono(proveedor.getTelefono());
        return dto;
    }
    public List<ProveedorDTO> obtenerTodos() {
        return proveedorRepository.findAll()
            .stream()
            .map(this::convertirADTO)
            .toList();
    }
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
}
