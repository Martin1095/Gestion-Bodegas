package com.gestion.articulo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.model.Articulo;

@Service
public class ArticuloValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(Articulo articulo){
        if(articulo.getMarca() == null || articulo.getMarca().trim().length() == 0){
            return false;
        }
        if(articulo.getNombre() == null || articulo.getNombre().trim().length() == 0){
            return false;
        }
        if(articulo.getPrecio() == null || articulo.getPrecio() == 0){
            return null;
        }
        if(articulo.getStock() == null || articulo.getStock() == 0){
            return null;
        }
        return true;
    }

        // Convertir entidad a DTO
    ArticuloDTO convertirADTO(Articulo articulo) {
        ArticuloDTO articuloDTO = new ArticuloDTO();
        articuloDTO.setId_articulo(articulo.getId_articulo());
        articuloDTO.setNombre(articulo.getNombre());
        articuloDTO.setMarca(articulo.getMarca());
        articuloDTO.setStock(articulo.getStock());
        articuloDTO.setPrecio(articulo.getPrecio());
            return articuloDTO;
    }
}
