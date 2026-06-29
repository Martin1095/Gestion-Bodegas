package com.gestion.recepcion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.model.Recepcion;
import com.gestion.recepcion.service.RecepcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Recepciones", description = "Operaciones para las recepcion en el sistema")
@RequestMapping("/api/v1/recepciones")

public class RecepcionController {

    @Autowired
    private RecepcionService recepcionService;

    @GetMapping
    @Operation(summary = "Obtener todas las recepciones", description = "Devuelve una lista de todas las recepciones registradas en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de recepciones obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron recepciones")
    public ResponseEntity<List<RecepcionDTO>> obtenerTodos() {
        List<RecepcionDTO> lista = recepcionService.obtenerRecepciones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Guardar recepcion", description = "Permite guardar una nueva recepcion en el sistema")
    @ApiResponse(responseCode = "201", description = "Recepcion guardada exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Recepcion> guardar(@RequestBody Recepcion recepcion) {
        try {
            Recepcion nueva = recepcionService.guardarRecepcion(recepcion);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}