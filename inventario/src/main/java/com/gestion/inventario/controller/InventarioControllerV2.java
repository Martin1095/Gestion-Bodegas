package com.gestion.inventario.controller;

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

import com.gestion.inventario.DTO.InventarioDTO;
import com.gestion.inventario.assemblers.InventarioModelAssembler;
import com.gestion.inventario.model.Inventario;
import com.gestion.inventario.service.InventarioService;

import jakarta.validation.Valid;

@RestController("inventarioControllerV2")
@RequestMapping("/api/v2/inventarios")
public class InventarioControllerV2 {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<InventarioDTO>>> obtenerInventarios() {
        List<EntityModel<InventarioDTO>> inventarios = inventarioService.obtenerInventarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                inventarios,
                linkTo(methodOn(InventarioController.class).listar()).withSelfRel()
        ));
    }


    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<InventarioDTO>> buscarPorId(@PathVariable Integer id) {
        try {
            InventarioDTO dto = inventarioService.buscarInventarioPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<InventarioDTO>> registrar(@Valid @RequestBody Inventario inventario) {
        try {
            InventarioDTO newInventario = inventarioService.guardarInventario(inventario);
            return ResponseEntity
                    .created(linkTo(methodOn(InventarioController.class).buscar(newInventario.getId_inventario())).toUri())
                    .body(assembler.toModel(newInventario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
