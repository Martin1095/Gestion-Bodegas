package com.gestion.despacho.DTO;

import java.sql.Date;
import lombok.Data;

@Data
public class DespachoDTO {

    // ID del despacho
    private Integer id_despacho;

    // Fecha del despacho
    private Date fecha;

    // Destino del envío
    private String destino;

    // Estado actual del despacho
    private String estado;

    // Cantidad despachada
    private Integer cantidad;
}
