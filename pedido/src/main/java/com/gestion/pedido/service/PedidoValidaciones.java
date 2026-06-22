package com.gestion.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.pedido.DTO.ClienteExternoDTO;
import com.gestion.pedido.DTO.PedidoDTO;
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
                .uri("http://clientes/api/v1/clientes/" + idClienteExterno)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ClienteExternoDTO.class)
                .block();

            if (resultado != null) {
                return resultado;
            }
            clienteRecuperado.setIdClienteExterno(0);
            clienteRecuperado.setNombre("Cliente no encontrado");
            return clienteRecuperado;

        } catch (Exception e) {
            return clienteRecuperado;
        }
    }

    //Metodo para convertir a PedidoDTO
    PedidoDTO convertirAPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setId_pedido(pedido.getId_pedido());
        pedidoDTO.setFecha_entrega(pedido.getFecha_entrega());
        pedidoDTO.setDireccion_entrega(pedido.getDireccion_entrega());
        pedidoDTO.setEstado_pedido(pedido.getEstado_pedido());
        pedidoDTO.setCliente(obtenerCliente(pedido.getId_pedido()));
        return pedidoDTO;
    }
}
