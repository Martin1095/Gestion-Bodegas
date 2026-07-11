package com.gestion.articulo.controller;

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

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.assemblers.ArticuloModelAssembler;
import com.gestion.articulo.model.Articulo;
import com.gestion.articulo.service.ArticuloService;

import jakarta.validation.Valid;

@RestController("articuloControllerV2")
@RequestMapping("/api/v2/articulos")
public class ArticuloControllerV2 {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ArticuloDTO>>> obtenerArticulos() {
        List<EntityModel<ArticuloDTO>> articulos = articuloService.obtenerArticulos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (articulos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                articulos,
                linkTo(methodOn(ArticuloController.class).obtenerTodos()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ArticuloDTO>> buscarPorId(@PathVariable Integer id) {
        try {
            ArticuloDTO dto = articuloService.obtenerArticuloPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ArticuloDTO>> registrar(@Valid @RequestBody Articulo articulo) {
        try {
            ArticuloDTO newArticulo = articuloService.guardarArticulo(articulo);
            return ResponseEntity
                    .created(linkTo(methodOn(ArticuloController.class).buscarPorId(newArticulo.getId_articulo())).toUri())
                    .body(assembler.toModel(newArticulo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
