package com.gestion.pedido.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestion.pedido.DTO.PedidoDTO;
import com.gestion.pedido.controller.PedidoController;
@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>>{

    @Override
    public EntityModel<PedidoDTO> toModel(PedidoDTO pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).obtenerPedidoPorId(pedido.getId_pedido())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).obtenerPedidos()).withRel("pedidos")
        );
    }
}
