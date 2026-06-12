package com.gestion.articulo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articulos")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_articulo;

    // Nombre del artículo
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    // Marca tecnológica
    @NotBlank(message = "La marca es obligatoria")
    @Column(nullable = false)
    private String marca;

    // Stock disponible
    @Min(value = 0)
    @Column(nullable = false)
    private Integer stock;

    // Precio del producto
    @Min(value = 1)
    @Column(nullable = false)
    private Double precio;
}
