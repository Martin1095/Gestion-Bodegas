package com.gestion.bodega.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.bodega.DTO.BodegaDTO;
import com.gestion.bodega.model.Bodega;
import com.gestion.bodega.controller.BodegaController;

@Component
public class BodegaModelAssembler implements RepresentationModelAssembler<BodegaDTO, EntityModel<BodegaDTO>>{

    @Override
    public EntityModel<BodegaDTO> toModel(BodegaDTO bodega) {
        return EntityModel.of(bodega,
                linkTo(methodOn(BodegaController.class).buscar(bodega.getId_bodega())).withSelfRel(),
                linkTo(methodOn(BodegaController.class).listar()).withRel("bodegas")
        );
    }
}
