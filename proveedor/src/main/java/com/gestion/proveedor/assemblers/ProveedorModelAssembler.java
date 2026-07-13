package com.gestion.proveedor.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.proveedor.DTO.ProveedorDTO;
import com.gestion.proveedor.controller.ProveedorController;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<ProveedorDTO, EntityModel<ProveedorDTO>> {

    @Override
    public EntityModel<ProveedorDTO> toModel(ProveedorDTO proveedor) {

        return EntityModel.of(proveedor,

                linkTo(methodOn(ProveedorController.class)
                        .obtenerPedidoPorId(proveedor.getId_proveedor()))
                        .withSelfRel(),

                linkTo(methodOn(ProveedorController.class)
                        .obtenerTodos())
                        .withRel("proveedores"));
    }

}
