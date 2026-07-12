package com.gestion.despacho.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.assemblers.DespachoModelAssembler;
import com.gestion.despacho.model.Despacho;
import com.gestion.despacho.service.DespachoService;

import jakarta.validation.Valid;

@RestController("despachoControllerV2")
@RequestMapping("/api/v2/despachos")
public class DespachoControllerV2 {

    @Autowired
    private DespachoService despachoService;

    @Autowired
    private DespachoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<DespachoDTO>>> obetenerDespachos() {
        List<EntityModel<DespachoDTO>> despachos = despachoService.obtenerDespachos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (despachos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                despachos,
                linkTo(methodOn(DespachoController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DespachoDTO>> porId(@PathVariable Integer id) {
        try {
            DespachoDTO dto = despachoService.buscarDespachoPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DespachoDTO>> registrar(@Valid @RequestBody Despacho despacho) {
        try {
            DespachoDTO newDespacho = despachoService.guardarDespacho(despacho);
            return ResponseEntity
                    .created(linkTo(methodOn(DespachoController.class).buscar(newDespacho.getId_despacho())).toUri())
                    .body(assembler.toModel(newDespacho));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
