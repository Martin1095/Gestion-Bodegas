package com.gestion.cliente.DTO;

import lombok.Data;

@Data
public class ClienteDTO {

    private Integer id_cliente;
    private String nombre;
    private String apellido;
    private String rut;
    private String correo;
    private String telefono;
}
