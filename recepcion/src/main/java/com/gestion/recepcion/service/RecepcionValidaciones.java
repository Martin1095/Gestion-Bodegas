package com.gestion.recepcion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.recepcion.DTO.ProveedorExternoDTO;
import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.model.Recepcion;

import reactor.core.publisher.Mono;

@Service
public class RecepcionValidaciones {

     @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(Recepcion recepcion) {

        if (recepcion.getFecha() == null) {
            return false;
        }

        if (recepcion.getCantidad() == null || recepcion.getCantidad() == 0) {
            return false;
        }

        return true;
    }

    public ProveedorExternoDTO obtenerProveedor(Integer idProveedor) {

        ProveedorExternoDTO proveedorRecuperado = new ProveedorExternoDTO();

        try {

            ProveedorExternoDTO resultado = webClientBuilder.build()
                    .get()
                    .uri("http://proveedores/api/v1/proveedores/" + idProveedor)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(ProveedorExternoDTO.class)
                    .block();

            if (resultado != null) {
                return resultado;
            }

            proveedorRecuperado.setId_proveedor(0);
            proveedorRecuperado.setNombre("Proveedor no encontrado");

            return proveedorRecuperado;

        } catch (Exception e) {
            return proveedorRecuperado;
        }
    }

    // Metodo para convertir a RecepcionDTO
    public RecepcionDTO convertirARecepcionDTO(Recepcion recepcion) {

        RecepcionDTO recepcionDTO = new RecepcionDTO();
        recepcionDTO.setId(recepcion.getId_recepcion());
        recepcionDTO.setFecha(recepcion.getFecha());
        recepcionDTO.setCantidad(recepcion.getCantidad());
        return recepcionDTO;
    }
}
