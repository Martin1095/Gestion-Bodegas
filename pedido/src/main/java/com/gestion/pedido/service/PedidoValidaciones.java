package com.gestion.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.pedido.DTO.ClienteExternoDTO;
import com.gestion.pedido.model.Pedido;

import reactor.core.publisher.Mono;

@Service
public class PedidoValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Boolean validarNullVacio(Pedido pedido){
        if(pedido.getFecha_entrega()== null || pedido.getDireccion_entrega().trim().length() == 0){
            return false;
        }
        if(pedido.getDireccion_entrega() == null || pedido.getDireccion_entrega().trim().length() == 0){
            return false;
        }
        if(pedido.getEstado_pedido() == null  || pedido.getEstado_pedido().trim().length() == 0){
            return false;
        }
        return true;
    }

        public ClienteExternoDTO obtenerCliente(Integer idClienteExterno){
        ClienteExternoDTO clienteRecuperado = new ClienteExternoDTO();
        try {
            ClienteExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("http://sables/api/v1/sables/buscar-por-jedi/")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ClienteExternoDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            clienteRecuperado.setIdClienteExterno(0);

            clienteRecuperado.setNombre(null);
            return clienteRecuperado;

        } catch (Exception e) {
            return clienteRecuperado;
        }
    }



}
