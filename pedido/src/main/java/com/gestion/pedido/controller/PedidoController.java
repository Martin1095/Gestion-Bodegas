package com.gestion.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.pedido.DTO.PedidoDTO;
import com.gestion.pedido.model.Pedido;
import com.gestion.pedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pedidos", description = "Operaciones para los pedidos en el sistema")
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Metodo para obtener todos los pedidos
    @GetMapping
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve una lista de todos los pedidos en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron pedidos")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidos() {
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidos();
        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Metodo para obtener un pedido por su ID
    @GetMapping("/{id_pedido}")
    @Operation(summary = "Obtener pedido por ID", description = "Permite buscar un pedido por su ID.")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Integer id_pedido) {
        try {
            PedidoDTO pedido = pedidoService.obtenerPedidoPorId(id_pedido);
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Metodo para añadir un pedido nuevo
    @PostMapping
    @Operation(summary = "Agregar pedido", description = "Permite añadir un nuevo pedido al sistema.")
    @ApiResponse(responseCode = "201", description = "Pedido agregado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Pedido> agregarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.agregarPedido(pedido);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo para actualizar un pedido
    @PostMapping("/{id_pedido}")
    @Operation(summary = "Actualizar pedido", description = "Permite actualizar un pedido existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer id_pedido, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarPedido(id_pedido, pedido);
            return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    // Metodo para eliminar un pedido por su ID
    @DeleteMapping("/eliminar/{id_pedido}")
    @Operation(summary = "Eliminar pedido", description = "Permite eliminar un pedido existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Pedido eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    public ResponseEntity<String> eliminarPedido(@PathVariable Integer id_pedido) {
        String mensaje = pedidoService.eliminarPedido(id_pedido);

        if (mensaje.contains("eliminado")) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            
        }
        
    }
}