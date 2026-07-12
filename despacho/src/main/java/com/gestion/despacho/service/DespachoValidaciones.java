package com.gestion.despacho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.DTO.PedidoExternoDTO;
import com.gestion.despacho.DTO.BodegaExternoDTO;
import com.gestion.despacho.model.Despacho;

import reactor.core.publisher.Mono;

@Service
public class DespachoValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Boolean validarNullVacio(Despacho despacho){
        if (despacho.getFecha() == null) {
            return null;
        }
        if(despacho.getDestino() == null || despacho.getDestino().trim().length() == 0){
            return null;
        }
        if (despacho.getEstado() == null || despacho.getEstado().trim().length() == 0) {
            return null;
        }
        if(despacho.getCantidad() == null || despacho.getCantidad() == 0){
            return null;
        }
        return true;
    }


    public DespachoDTO convertirADTO(Despacho despacho){
        DespachoDTO despachoDTO = new DespachoDTO();
        despachoDTO.setId_despacho(despacho.getId_despacho());
        if (despacho.getFecha() != null) {
            despachoDTO.setFecha(new java.sql.Date(despacho.getFecha().getTime()));
        }
        despachoDTO.setDestino(despacho.getDestino());
        despachoDTO.setEstado(despacho.getEstado());
        despachoDTO.setCantidad(despacho.getCantidad());
        despachoDTO.setPedido(obtenerPedido(despacho.getId_pedido()));
        despachoDTO.setBodega(obtenerBodega(despacho.getId_bodega()));
        return despachoDTO;
    }

    public PedidoExternoDTO obtenerPedido(Integer idPedido){
        PedidoExternoDTO pedidoRecuperado = new PedidoExternoDTO();
            try{
                PedidoExternoDTO resultado = webClientBuilder.build()
                    .get()
                    .uri("http://pedidos/api/v1/pedidos/" + idPedido)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(PedidoExternoDTO.class)
                    .block();
                if(resultado != null){
                    return resultado;
                }
                pedidoRecuperado.setIdPedidoExterno(0);
                return pedidoRecuperado;
            }catch(Exception e){
                return pedidoRecuperado;
            }
    }

    public BodegaExternoDTO obtenerBodega(Integer idBodega){
        BodegaExternoDTO bodegaRecuperada = new BodegaExternoDTO();
            try{
                BodegaExternoDTO resultado = webClientBuilder.build()
                    .get()
                    .uri("http://bodega/api/v1/bodegas/" + idBodega)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(BodegaExternoDTO.class)
                    .block();
                if(resultado != null){
                    return resultado;
                }
                bodegaRecuperada.setIdBodegaExterno(0);
                return bodegaRecuperada;
            }catch(Exception e){
                return bodegaRecuperada;
            }
    }

}
