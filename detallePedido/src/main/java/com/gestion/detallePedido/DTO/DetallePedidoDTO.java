package com.gestion.detallePedido.DTO;

import lombok.Data;

@Data
public class DetallePedidoDTO {

    private Integer id_detalle_pedido;
    private int cantidad;
    private double precio_unitario;
}

