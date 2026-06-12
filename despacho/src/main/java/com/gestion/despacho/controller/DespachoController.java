package com.gestion.despacho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.service.DespachoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Despachos", description = "Operaciones para los despachos en el sistema")
@RequestMapping("/api/v1/despachos")
public class DespachoController {

    @Autowired
    private DespachoService service;

    @GetMapping
    @Operation(summary = "Listar datos de despachos", description = "Devuelve una lista con informacion sobre los despachos.")
    @ApiResponse(responseCode = "200", description = "Lista de despachos obtenida exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<List<Despacho>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Operation(summary = "Guardar despacho", description = "Permite guardar un nuevo despacho en el sistema.")
    @ApiResponse(responseCode = "201", description = "Despacho guardado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Despacho> guardar(@RequestBody Despacho despacho){
        return new ResponseEntity<>(service.guardar(despacho),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar despacho por ID", description = "Permite buscar un despacho por su ID.")
    @ApiResponse(responseCode = "200", description = "Despacho encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Despacho no encontrado")
    public ResponseEntity<Despacho> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscar(id));
    }

}