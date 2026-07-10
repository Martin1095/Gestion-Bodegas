package com.gestion.inventario.DTO;

import lombok.Data;

@Data
public class InventarioDTO {

    private Integer id_inventario;
    private Integer stockActual;
    private Integer stockMinimo;
    private String ubicacion;

    private BodegaExternaDTO bodega;
}