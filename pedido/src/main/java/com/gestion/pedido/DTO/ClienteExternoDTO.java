package com.gestion.pedido.DTO;

import lombok.Data;

@Data
public class ClienteExternoDTO {
    private Integer idClienteExterno;
    private Integer id_pedido;
    private String nombre;
    private String apellido;
    private String rut;
    private String correo;
    private String telefono;
}
