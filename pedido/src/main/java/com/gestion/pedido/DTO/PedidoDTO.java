package com.gestion.pedido.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PedidoDTO {

    private Integer id_pedido;
    private Date fecha_entrega;
    private String direccion_entrega;
    private String estado_pedido;

    private ClienteExternoDTO cliente;
}