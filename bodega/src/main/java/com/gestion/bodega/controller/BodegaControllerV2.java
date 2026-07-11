package com.gestion.bodega.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.bodega.DTO.BodegaDTO;
import com.gestion.bodega.assemblers.BodegaModelAssembler;
import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.service.BodegaService;

import jakarta.validation.Valid;

@RestController("bodegaControllerV2")
@RequestMapping("/api/v2/bodegas")
public class BodegaControllerV2 {

    @Autowired
    private BodegaService bodegaService;

    @Autowired
    private BodegaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BodegaDTO>>> todas() {
        List<EntityModel<BodegaDTO>> bodegas = bodegaService.obtenerBodegas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (bodegas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                bodegas,
                linkTo(methodOn(BodegaController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BodegaDTO>> porId(@PathVariable Integer id) {
        try {
            BodegaDTO dto = bodegaService.buscarBodegaPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BodegaDTO>> registrar(@Valid @RequestBody Bodega bodega) {
        try {
            BodegaDTO newBodega = bodegaService.guardarBodega(bodega);
            return ResponseEntity
                    .created(linkTo(methodOn(BodegaController.class).buscar(newBodega.getId_bodega())).toUri())
                    .body(assembler.toModel(newBodega));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
