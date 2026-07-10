package com.gestion.inventario.DTO;

import lombok.Data;

@Data
public class BodegaExternaDTO {

    private Integer idBodegaExterna;
    private Integer id_inventario;
    private String nombre;
    private String descripcion;
    private String direccion;
}
