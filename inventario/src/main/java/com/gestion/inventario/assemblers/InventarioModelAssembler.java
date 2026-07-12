package com.gestion.inventario.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.inventario.DTO.InventarioDTO;
import com.gestion.inventario.controller.InventarioController;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<InventarioDTO, EntityModel<InventarioDTO>> {

    @Override
    public EntityModel<InventarioDTO> toModel(InventarioDTO inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioController.class).buscar(inventario.getId_inventario())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).listar()).withRel("inventarios")
        );
    }
}
