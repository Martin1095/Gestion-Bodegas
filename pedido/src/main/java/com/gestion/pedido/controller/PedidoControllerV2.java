package com.gestion.pedido.controller;

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

import com.gestion.pedido.DTO.PedidoDTO;
import com.gestion.pedido.assemblers.PedidoModelAssembler;
import com.gestion.pedido.model.Pedido;
import com.gestion.pedido.service.PedidoService;

import jakarta.validation.Valid;

@RestController("pedidoControllerV2")
@RequestMapping("/api/v2/pedidos")
public class PedidoControllerV2 {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<PedidoDTO>>> obtenerPedidos() {
        List<EntityModel<PedidoDTO>> pedidos = pedidoService.obtenerPedidos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                pedidos,
                linkTo(methodOn(PedidoController.class).obtenerPedidos()).withSelfRel()
        ));
    }


    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PedidoDTO>> buscarPorId(@PathVariable Integer id) {
        try {
            PedidoDTO dto = pedidoService.obtenerPedidoPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PedidoDTO>> registrar(@Valid @RequestBody Pedido pedido) {
        try {
            PedidoDTO newPedido = pedidoService.guardarPedido(pedido);
            return ResponseEntity
                    .created(linkTo(methodOn(PedidoController.class).obtenerPedidoPorId(newPedido.getId_pedido())).toUri())
                    .body(assembler.toModel(newPedido));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
