package com.gestion.detallePedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.detallePedido.DTO.ArticuloExternoDTO;
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

    //Obtener Pedido externo
    public PedidoExternoDTO obtenerPedido(Integer idPedidoExterno){
        PedidoExternoDTO pedidoRecuperado = new PedidoExternoDTO();
        try{
            PedidoExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("https://Pedidos/api/v1/Pedidos/" + idPedidoExterno)
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

    //Obtener Articulo externo
    public ArticuloExternoDTO obtenerArticulo(Integer id_articuloExterno){
        ArticuloExternoDTO articuloRecuperado = new ArticuloExternoDTO();
        try{
            ArticuloExternoDTO resultado = webClientBuilder.build()
                .get()
                .uri("https://Articulos/api/v1/Articulos/" + id_articuloExterno)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ArticuloExternoDTO.class)
                .block();
        
        if (resultado != null){
            return resultado;
        }
        articuloRecuperado.setId_articuloExterno(id_articuloExterno);
        articuloRecuperado.setNombre("Articulo no encontrado");
        return articuloRecuperado;

        }catch (Exception e){
            return articuloRecuperado;
        }
    }

    //Metodo para transformar un DetallePedido a DetallePedidoDTO
    DetallePedidoDTO convertirADetallePedidoDTO(DetallePedido detallePedido) {
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
        detallePedidoDTO.setId_detalle_pedido(detallePedido.getId_detalle_pedido());
        detallePedidoDTO.setCantidad(detallePedido.getCantidad());
        detallePedidoDTO.setPrecio_unitario(detallePedido.getPrecio_unitario());
        detallePedidoDTO.setPedido(obtenerPedido(detallePedido.getId_detalle_pedido()));
        detallePedidoDTO.setArticulo(obtenerArticulo(detallePedido.getId_detalle_pedido()));;
        return detallePedidoDTO;
    }

}
