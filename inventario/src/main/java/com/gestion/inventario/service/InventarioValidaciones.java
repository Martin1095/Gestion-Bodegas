package com.gestion.inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.inventario.DTO.BodegaExternaDTO;
import com.gestion.inventario.DTO.InventarioDTO;
import com.gestion.inventario.model.Inventario;

import reactor.core.publisher.Mono;

@Service

public class InventarioValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

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

        public BodegaExternaDTO obtenerBodega(Integer idBodegaExterna){
        BodegaExternaDTO bodegaRecuperada = new BodegaExternaDTO();
        try {
            BodegaExternaDTO resultado = webClientBuilder.build()
                .get()
                .uri("http://bodegas/api/v1/bodegas/" + idBodegaExterna)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(BodegaExternaDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            bodegaRecuperada.setIdBodegaExterna(0);
            bodegaRecuperada.setNombre("Bodega no encontrada");
            return bodegaRecuperada;

        } catch (Exception e) {
            return bodegaRecuperada;
        }
    }

    public InventarioDTO convertirADTO(Inventario inventario){
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setId_inventario(inventario.getId_inventario());
        inventarioDTO.setStockActual(inventario.getId_inventario());
        inventarioDTO.setStockMinimo(inventario.getStockMinimo());
        inventarioDTO.setUbicacion(inventario.getUbicacion());
        inventarioDTO.setBodega(obtenerBodega(inventario.getId_inventario()));
        return inventarioDTO;
    }

}
