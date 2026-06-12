package com.gestion.detallePedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gestion.detallePedido.DTO.DetallePedidoDTO;
import com.gestion.detallePedido.model.DetallePedido;
import com.gestion.detallePedido.service.DetallePedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "Detalles de Pedidos", description = "Operaciones para los detalles de pedido en el sistema")
@RequestMapping("/api/v1/detalle_pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    // Metodo para convertir un DetallePedido a DetallePedidoDTO
    public DetallePedidoDTO convertirADetallePedidoDTO(DetallePedido detallePedido) {
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
        detallePedidoDTO.setId_detalle_pedido(detallePedido.getId_detalle_pedido());
        detallePedidoDTO.setCantidad(detallePedido.getCantidad());
        detallePedidoDTO.setPrecio_unitario(detallePedido.getPrecio_unitario());
        return detallePedidoDTO;
    }

    // Metodo para obtener todos los detalles de pedido
    @GetMapping
    @Operation(summary = "Obtener todos los detalles de pedidos", description = "Devuelve una lista de todos los detalles de pedido en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de detalles de pedido obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron detalles de pedido")
    public ResponseEntity<List<DetallePedidoDTO>> obtenerDetallesPedido() {
        List<DetallePedidoDTO> detallesPedido = detallePedidoService.obtenerDetallesPedido();
        if (detallesPedido.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(detallesPedido, HttpStatus.OK);
    }

    // Metodo para obtener un detalle de pedido por su ID
    @GetMapping("/{id_detalle_pedido}")
    @Operation(summary = "Obtener detalle de pedido por ID", description = "Permite buscar un detalle de pedido por su ID.")
    @ApiResponse(responseCode = "200", description = "Detalle de pedido encontrado exitosamente")
    @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    public ResponseEntity<DetallePedidoDTO> obtenerDetallePedidoPorId(@PathVariable Integer id_detalle_pedido) {
        try {
            DetallePedidoDTO detallePedido = detallePedidoService.obtenerDetallePedidoPorId(id_detalle_pedido);
            return new ResponseEntity<>(detallePedido, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Metodo para añadir un nuevo detalle de pedido
    @PostMapping
    @Operation(summary = "Agregar detalle de pedido", description = "Permite añadir un nuevo detalle de pedido al sistema.")
    @ApiResponse(responseCode = "201", description = "Detalle de pedido agregado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<DetallePedido> agregarDetallePedido(@RequestBody DetallePedido detallePedido) {
        try {
            DetallePedido nuevoDetallePedido = detallePedidoService.agregarDetallePedido(detallePedido);
            return new ResponseEntity<>(nuevoDetallePedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo para editar un detalle de pedido por su ID
    @PostMapping("/editar/{id_detalle_pedido}")
    @Operation(summary = "Editar detalle de pedido", description = "Permite editar un detalle de pedido existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Detalle de pedido actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    public ResponseEntity<DetallePedido> editarDetallePedido(@PathVariable Integer id_detalle_pedido, @RequestBody DetallePedido detallePedido) {
        try {
            DetallePedido detallePedidoActualizado = detallePedidoService.editarDetallePedido(id_detalle_pedido, detallePedido);
            return new ResponseEntity<>(detallePedidoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Metodo para eliminar un detalle de pedido por su ID
    @PostMapping("/eliminar/{id_detalle_pedido}")
    @Operation(summary = "Eliminar detalle de pedido", description = "Permite eliminar un detalle de pedido existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Detalle de pedido eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Detalle de pedido no encontrado")
    public ResponseEntity<String> eliminarDetallePedido(@PathVariable Integer id_detalle_pedido) {
        String resultadoEliminacion = detallePedidoService.eliminarDetallePedido(id_detalle_pedido);
        if (resultadoEliminacion.contains("exitosamente")) {
            return new ResponseEntity<>(resultadoEliminacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultadoEliminacion, HttpStatus.NOT_FOUND);
        }
    }

}
