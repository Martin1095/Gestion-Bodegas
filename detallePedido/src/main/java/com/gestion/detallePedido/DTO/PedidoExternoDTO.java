package com.gestion.detallePedido.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PedidoExternoDTO {

    private Integer idPedidoExterno;
    private Date fecha_entrega;
    private String direccion_entrega;
    private String estado_pedido;
}
