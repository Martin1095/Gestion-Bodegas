package com.gestion.despacho.DTO;

import java.sql.Date;
import lombok.Data;

@Data
public class PedidoExternoDTO {
    private Integer idPedidoExterno;
    private Integer id_pedido;
    private Date fecha_entrega;
    private String direccion_entrega;
    private String estado_pedido;
}
