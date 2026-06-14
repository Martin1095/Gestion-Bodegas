package com.gestion.cliente.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Método para obtener todos los clientes
    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientesen el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    @ApiResponse(responseCode = "204", description = "No se encontraron clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerClientes();
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Metodo para obtener un cliente por su ID
    @GetMapping("/{id_cliente}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve la información de un cliente específico según su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Integer id_cliente) {
        try {
            ClienteDTO cliente = clienteService.obtenerClientePorId(id_cliente);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para buscar un cliente por su RUT
    @GetMapping("/buscar/{rut}")
    @Operation(summary = "Buscar cliente por RUT", description = "Devuelve la información de un cliente específico según su RUT.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente")
    @ApiResponse(responseCode = "204", description = "Cliente no encontrado")
    public ResponseEntity<ClienteDTO> buscarPorRut(@PathVariable String rut) {
        try {
            ClienteDTO cliente = clienteService.buscarPorRut(rut);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para añadir un nuevo cliente
    @PostMapping
    @Operation(summary = "Agregar cliente", description = "Añade un nuevo cliente al sistema.")
    @ApiResponse(responseCode = "201", description = "Cliente agregado exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.agregarCliente(cliente);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Método para actualizar un cliente existente
    @PatchMapping("/{id_cliente}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza la información de un cliente existente.")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id_cliente, @RequestBody Cliente clienteActu) {
        try {
            Cliente cliente = clienteService.actualizarCliente(id_cliente, clienteActu);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // metodo para eliminar un cliente por su ID
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema.")
    @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id) {
        String mensaje = clienteService.eliminarCliente(id);
        if (mensaje.contains("Exitosamente")) {
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        
    }
}
