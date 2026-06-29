package com.gestion.trabajador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.trabajador.DTO.TrabajadorDTO;
import com.gestion.trabajador.model.Trabajador;
import com.gestion.trabajador.service.TrabajadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Trabajadores", description = "Operaciones para los trabajadores en el sistema")
@RequestMapping("/api/v1/trabajadores")
public class TrabajadorController {
    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores", description = "Devuelve una lista de todos los trabajadores en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de trabajadores obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron trabajadores")
    public ResponseEntity<List<TrabajadorDTO>> obtenerTodos() {
        List<TrabajadorDTO> lista = trabajadorService.obtenerTrabajadores();
        
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping
    @Operation(summary = "Guardar trabajador", description = "Permite guardar un nuevo trabajador en el sistema")
    @ApiResponse(responseCode = "201", description = "Trabajador guardado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Trabajador> guardar(@RequestBody Trabajador trabajador) {

        try {
            Trabajador nuevo = trabajadorService.guardarTrabajador(trabajador);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}