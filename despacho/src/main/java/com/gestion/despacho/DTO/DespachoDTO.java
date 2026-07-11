package com.gestion.despacho.DTO;

import java.sql.Date;
import lombok.Data;

@Data
public class DespachoDTO {

    private PedidoExternoDTO pedido;
    private BodegaExternoDTO bodega;
    private Integer id_despacho;
    private Date fecha;
    private String destino;
    private String estado;
    private Integer cantidad;
}
