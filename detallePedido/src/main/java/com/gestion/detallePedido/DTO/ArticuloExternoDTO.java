package com.gestion.detallePedido.DTO;

import lombok.Data;

@Data
public class ArticuloExternoDTO {
    private Integer id_articuloExterno;
    private Integer id_Detalle_pedido;
    private String nombre;
    private String marca;
    private Integer stock;
    private Double precio;
}
