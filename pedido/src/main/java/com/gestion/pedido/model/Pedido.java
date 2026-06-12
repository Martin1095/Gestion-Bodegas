package com.gestion.pedido.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    @NotBlank(message = "La fecha de entrega es obligatoria")
    @Size(min = 10, max = 10, message = "La fecha de entrega debe tener 10 caracteres")
    @Column(name = "fecha_entrega", nullable = false, length = 10)
    private Date fecha_entrega;

    @NotBlank(message = "La dirección de entrega es obligatoria")
    @Size(max = 100, message = "La dirección de entrega no puede tener más de 100 caracteres")
    @Column(name = "direccion_entrega", nullable = false, length = 100)
    private String direccion_entrega;

    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(max = 20, message = "El estado del pedido no puede tener más de 20 caracteres")
    @Column(name = "estado_pedido", nullable = false, length = 20)
    private String estado_pedido;
}
