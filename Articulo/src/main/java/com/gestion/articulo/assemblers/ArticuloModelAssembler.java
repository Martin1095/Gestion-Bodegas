package com.gestion.articulo.assemblers;

import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.gestion.articulo.DTO.ArticuloDTO;
import com.gestion.articulo.controller.ArticuloController;

@Component
public class ArticuloModelAssembler implements RepresentationModelAssembler<ArticuloDTO, EntityModel<ArticuloDTO>> {

    @Override
    public EntityModel<ArticuloDTO> toModel(ArticuloDTO articulo) {
        return EntityModel.of(articulo,
                linkTo(methodOn(ArticuloController.class).buscarPorId(articulo.getId_articulo())).withSelfRel(),
                linkTo(methodOn(ArticuloController.class).obtenerTodos()).withRel("articulos")
        );
    }
}