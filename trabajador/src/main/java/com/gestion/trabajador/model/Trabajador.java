package com.gestion.trabajador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "trabajadores")
public class Trabajador {

    //identificador unico del trabajador, se genera automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_trabajador;

    //nombre del trabajador, no puede ser nulo ni vacio, debe tener entre 3 y 100 caracteres
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nombre;

    //cargo del trabajador, no puede ser nulo ni vacio
    @NotBlank
    @Column(nullable = false)
    private String cargo;

    //edad del trabajador, debe ser mayor o igual a 18
    @Min(value = 18)
    @Column(nullable = false)
    private Integer edad;

}