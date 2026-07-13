package com.gestion.trabajador.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.trabajador.DTO.TrabajadorDTO;
import com.gestion.trabajador.controller.TrabajadorController;

@Component
public class TrabajadorModelAssembler implements RepresentationModelAssembler<TrabajadorDTO, EntityModel<TrabajadorDTO>> {

    @Override
    public EntityModel<TrabajadorDTO> toModel(TrabajadorDTO trabajador) {

        return EntityModel.of(trabajador,

                linkTo(methodOn(TrabajadorController.class)
                        .obtenerTrabajadorPorId(trabajador.getId()))
                        .withSelfRel(),

                linkTo(methodOn(TrabajadorController.class)
                        .obtenerTodos())
                        .withRel("trabajadores"));
    }

}