package com.gestion.detallePedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.detallePedido.DTO.DetallePedidoDTO;
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

    public PedidoExternoDTO obtenerPedido(Integer idPedidoExterno){
        PedidoExternoDTO pedidoRecuperado = new PedidoExternoDTO();
        try{
            PedidoExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("https://detallePedidos/api/v1/detallesPedidos/" + idPedidoExterno)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(PedidoExternoDTO.class)
                .block();
        
        if (resultado != null){
            return resultado;
        }
        pedidoRecuperado.setIdPedidoExterno(idPedidoExterno);
        pedidoRecuperado.setEstado_pedido("Pedido no encontrado");
        return pedidoRecuperado;

        }catch (Exception e){
            return pedidoRecuperado;
        }
    }

    //Metodo para transformar un DetallePedido a DetallePedidoDTO
    public DetallePedidoDTO convertirADetallePedidoDTO(DetallePedido detallePedido) {
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
        detallePedidoDTO.setId_detalle_pedido(detallePedido.getId_detalle_pedido());
        detallePedidoDTO.setCantidad(detallePedido.getCantidad());
        detallePedidoDTO.setPrecio_unitario(detallePedido.getPrecio_unitario());
        detallePedidoDTO.setPedido(obtenerPedido(detallePedido.getId_detalle_pedido()));
        return detallePedidoDTO;
    }

}
