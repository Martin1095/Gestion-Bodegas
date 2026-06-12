package com.gestion.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.inventario.model.Inventario;
import com.gestion.inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Inventario", description = "Controlador para gestionar el inventario de la bodega")
@RequestMapping("/api/v1/inventarios")

public class InventarioController {

    @Autowired
    private InventarioService service;

    @GetMapping
    @Operation(summary = "Listar inventario", description = "Obtiene una lista de todos los inventarios registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de inventarios obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron inventarios")
    public ResponseEntity<List<Inventario>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Operation(summary = "Guardar inventario", description = "Permite guardar un nuevo inventario en el sistema.")
    @ApiResponse(responseCode = "201", description = "Inventario guardado exitosamente")
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inv){
        return new ResponseEntity<>(service.guardar(inv),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inventario", description = "Permite buscar un inventario por su ID.")
    @ApiResponse(responseCode = "200", description = "Inventario encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    public ResponseEntity<Inventario> buscar(@PathVariable Integer id){
    return ResponseEntity.ok(service.buscar(id));
    }

}
