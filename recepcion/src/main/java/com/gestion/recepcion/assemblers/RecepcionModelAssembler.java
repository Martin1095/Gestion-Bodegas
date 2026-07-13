package com.gestion.recepcion.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.recepcion.DTO.RecepcionDTO;
import com.gestion.recepcion.controller.RecepcionController;

@Component
public class RecepcionModelAssembler implements RepresentationModelAssembler<RecepcionDTO, EntityModel<RecepcionDTO>> {

    @Override
    public EntityModel<RecepcionDTO> toModel(RecepcionDTO recepcion) {

        return EntityModel.of(recepcion,

                linkTo(methodOn(RecepcionController.class)
                        .obtenerRecepcionPorId(recepcion.getId()))
                        .withSelfRel(),

                linkTo(methodOn(RecepcionController.class)
                        .obtenerTodos())
                        .withRel("recepciones"));
    }

}
