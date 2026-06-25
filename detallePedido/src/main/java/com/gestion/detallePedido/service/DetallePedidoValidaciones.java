package com.gestion.detallePedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.detallePedido.DTO.PedidoExternoDTO;
import com.gestion.detallePedido.model.DetallePedido;

import reactor.core.publisher.Mono;

@Service
public class DetallePedidoValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(DetallePedido detallePedido){
        if(detallePedido.getCantidad() == 0 || detallePedido.getCantidad() == 0){
            return false;
        }
        if(detallePedido.getPrecio_unitario() == 0 || detallePedido.getPrecio_unitario()== 0){
            return false;
        }
        return true;
    }


}
