package com.gestion.despacho.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.despacho.DTO.DespachoDTO;
import com.gestion.despacho.controller.DespachoController;

@Component
public class DespachoModelAssembler implements RepresentationModelAssembler<DespachoDTO, EntityModel<DespachoDTO>> {

    @Override
    public EntityModel<DespachoDTO> toModel(DespachoDTO despacho) {
        return EntityModel.of(despacho,
                linkTo(methodOn(DespachoController.class).buscar(despacho.getId_despacho())).withSelfRel(),
                linkTo(methodOn(DespachoController.class).listar()).withRel("despachos")
        );
    }
}
