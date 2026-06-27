package com.gestion.bodega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.bodega.DTO.BodegaDTO;
import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.service.BodegaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Bodega ", description = "Operaciones para la bodega en el sistema")
@RequestMapping("/api/v1/bodegas")

public class BodegaController {

    @Autowired
    private BodegaService service;

    @GetMapping
    @Operation(summary = "Listar datos de bodega", description = "Devuelve una lista con informacion de la bodega.")
    @ApiResponse(responseCode = "200", description = "Lista de bodegas obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron bodegas")
    public ResponseEntity<List<BodegaDTO>> listar(){
        return new ResponseEntity<List<BodegaDTO>>(service.obtenerBodegas(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Guardar bodega", description = "Permite guardar una nueva bodega en el sistema.")
    @ApiResponse(responseCode = "201", description = "Bodega guardada exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<BodegaDTO> guardar(@RequestBody Bodega bodega){
        return new ResponseEntity<>(service.guardarBodega(bodega),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar bodega por ID", description = "Permite buscar una bodega por su ID.")
    @ApiResponse(responseCode = "200", description = "Bodega encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    public ResponseEntity<BodegaDTO> buscar(@PathVariable Integer id){
        return new ResponseEntity<>(service.buscarBodegaPorId(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar bodega", description = "Permite eliminar una bodega del sistema.")
    @ApiResponse(responseCode = "200", description = "Bodega eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        service.eliminar(id);
        return new ResponseEntity<>("Eliminado",HttpStatus.OK);
    }

}

