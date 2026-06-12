package com.gestion.proveedor.DTO;
import lombok.Data;

@Data
public class ProveedorDTO {
    private Integer id_proveedor;
    private String nombre;
    private String correo;
    private String telefono;
}
