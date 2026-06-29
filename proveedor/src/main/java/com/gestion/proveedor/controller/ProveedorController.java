package com.gestion.proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.proveedor.DTO.ProveedorDTO;
import com.gestion.proveedor.model.Proveedor;
import com.gestion.proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Proveedores", description = "Operaciones para los proveedores en el sistema")
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Devuelve una lista de todos los proveedores en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron proveedores")
    public ResponseEntity<List<ProveedorDTO>> obtenerTodos() {
        List<ProveedorDTO> lista = proveedorService.obtenerProveedores();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Guardar proveedor", description = "Permite guardar un nuevo proveedor en el sistema")
    @ApiResponse(responseCode = "201", description = "Proveedor guardado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor nuevo = proveedorService.guardarProveedor(proveedor);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id_proveedor}")
    @Operation(summary = "Obtener proveedor por ID", description = "Permite buscar un proveedor por su ID.")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<ProveedorDTO> obtenerPedidoPorId(@PathVariable Integer id_proveedor) {
        try {
            ProveedorDTO proveedor = proveedorService.obtenerProveedorPorId(id_proveedor);
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}