package com.gestion.recepcion.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class RecepcionDTO {

    private Integer id;
    private Date fecha;
    private Integer cantidad;
}
