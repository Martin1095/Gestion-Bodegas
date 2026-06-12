package com.gestion.cliente.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;


    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre no puede tener más de 20 caracteres")
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 20, message = "El apellido no puede tener más de 20 caracteres")
    @Column(name = "apellido", nullable = false, length = 20)
    private String apellido;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(min = 10, max = 10, message = "El RUT debe tener 10 caracteres")
    @Column(name = "rut", nullable = false, unique = true, length = 10)
    private String rut;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 60, message = "El correo no puede tener más de 60 caracteres")
    @Column(name = "correo", nullable = false, length = 60)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 9, max = 9, message = "El teléfono debe tener exactamente 9 caracteres")
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

}
