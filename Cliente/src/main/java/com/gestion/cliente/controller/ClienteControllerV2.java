package com.gestion.cliente.controller;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.assemblers.ClienteModelAssembler;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.service.ClienteService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("clienteControllerV2")
@RequestMapping("/api/v2/clientes")
public class ClienteControllerV2 {


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO>>> todas() {
        List<EntityModel<ClienteDTO>> clientes = clienteService.obtenerClientes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteController.class).obtenerClientes()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ClienteDTO>> porId(@PathVariable Integer id) {
        try {
            ClienteDTO dto = clienteService.buscarClientePorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ClienteDTO>> registrar(@Valid @RequestBody Cliente cliente) {
        try {
            ClienteDTO newCliente = clienteService.guardarCliente(cliente);
            return ResponseEntity
                    .created(linkTo(methodOn(ClienteController.class).obtenerClientePorId(newCliente.getId_cliente())).toUri())
                    .body(assembler.toModel(newCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}