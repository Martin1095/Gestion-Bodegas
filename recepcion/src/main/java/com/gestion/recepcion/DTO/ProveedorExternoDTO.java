package com.gestion.recepcion.DTO;

import lombok.Data;

@Data
public class ProveedorExternoDTO {
    private Integer id_proveedor;
    private String nombre;
    private String correo;
    private String telefono;

}
