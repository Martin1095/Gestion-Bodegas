package com.gestion.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.inventario.DTO.InventarioDTO;
import com.gestion.inventario.model.Inventario;
import com.gestion.inventario.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioValidaciones inventarioValidaciones;

    @Autowired
    private InventarioRepository repo;

    public List<InventarioDTO> obtenerInventarios() {
        List<InventarioDTO> listaDTOs = new ArrayList<>();
        for (Inventario inventario : repo.findAll()) {
            listaDTOs.add(inventarioValidaciones.convertirADTO(inventario));
        }
        return listaDTOs;
    }

    public InventarioDTO guardarInventario(Inventario nuevoInventario) {
        if(inventarioValidaciones.validarNullVacio(nuevoInventario)){
            Inventario guardado = repo.save(nuevoInventario);
            return inventarioValidaciones.convertirADTO(guardado);
        }
        return null;
    }

    public InventarioDTO buscarInventarioPorId(Integer id_inventario) {
        Inventario inventario = repo.findById(id_inventario)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado en los archivos"));
        return inventarioValidaciones.convertirADTO(inventario);
    }

}
