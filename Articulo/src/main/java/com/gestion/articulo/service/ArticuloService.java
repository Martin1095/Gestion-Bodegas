package com.gestion.articulo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.model.Articulo;
import com.gestion.articulo.repository.ArticuloRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private ArticuloValidaciones articuloValidaciones;

    // Obtener todos los artículos
    public List<ArticuloDTO> obtenerArticulos(){
        List<ArticuloDTO> listaDTOs = new ArrayList<>();
        for (Articulo articulo : articuloRepository.findAll()) {
            listaDTOs.add(articuloValidaciones.convertirADTO(articulo));
        }
        return listaDTOs;
    }
    // Buscar artículo por id
    public ArticuloDTO obtenerArticuloPorId(Integer id_articulo){
        Articulo articulo = articuloRepository.findById(id_articulo)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id_articulo));
        return articuloValidaciones.convertirADTO(articulo);
    }
    // Guardar artículo
    public ArticuloDTO guardarArticulo(Articulo articulo) {
        if(articuloValidaciones.validarNullVacio(articulo)){
            Articulo guardado = articuloRepository.save(articulo);
            return articuloValidaciones.convertirADTO(guardado);
        }
        return null;
    }
    // Eliminar artículo
    public String eliminarArticulo(Integer id) {
        try {
            Articulo articulo = articuloRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
            articuloRepository.delete(articulo);
            return "Artículo eliminado correctamente";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    // Actualización parcial PATCH
    public Articulo actualizarArticulo(Integer id, Articulo articulo) {
        Articulo art = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
        if (articulo.getNombre() != null) {
            art.setNombre(articulo.getNombre());
        }
        if (articulo.getMarca() != null) {
            art.setMarca(articulo.getMarca());
        }
        if (articulo.getStock() != null) {
            art.setStock(articulo.getStock());
        }
        if (articulo.getPrecio() != null) {
            art.setPrecio(articulo.getPrecio());
        }
        return articuloRepository.save(art);
    }

}

