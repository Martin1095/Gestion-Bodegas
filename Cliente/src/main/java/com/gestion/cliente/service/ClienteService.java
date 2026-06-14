package com.gestion.cliente.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.cliente.DTO.ClienteDTO;
import com.gestion.cliente.model.Cliente;
import com.gestion.cliente.repository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para obtener todos los clientes
    public List<ClienteDTO> obtenerClientes(){
        return clienteRepository.findAll().stream()
                .map(this::convertirAClienteDTO)
                .toList();
    }

    // Metodo para obtener un cliente por su ID
    public ClienteDTO obtenerClientePorId(Integer id_cliente){
        Cliente cliente = clienteRepository.findById(id_cliente)
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id_cliente));
        return convertirAClienteDTO(cliente);
    }

    // Metodo para eliminar un cliente por su ID
    public String eliminarCliente(Integer id_cliente) {
        try {
            Cliente cliente = clienteRepository.findById(id_cliente)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El cliente con ID " + id_cliente + " no existe."));
            clienteRepository.delete(cliente);
            return "El cliente '" + cliente.getNombre() + "' ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    // Método para añadir un nuevo cliente
    public Cliente agregarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para actualizar un cliente existente
    public Cliente actualizarCliente(Integer id_cliente, Cliente clienteActu) {
        Cliente cliente = clienteRepository.findById(id_cliente).orElseThrow(() -> new RuntimeException("¡Imposible editar! El cliente con ID " + id_cliente + " no existe."));
        
        if(clienteActu.getNombre() != null) {
            cliente.setNombre(clienteActu.getNombre());
        }
        if(clienteActu.getCorreo() != null) {
            cliente.setCorreo(clienteActu.getCorreo());
        }
        if(clienteActu.getTelefono() != null) {
            cliente.setTelefono(clienteActu.getTelefono());
        }
        return clienteRepository.save(cliente);
    }

    // Método para buscar cliente por RUT
    public ClienteDTO buscarPorRut(String rut) {
        Cliente cliente = clienteRepository.findByRut(rut);
        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado con RUT: " + rut);
        }
        return convertirAClienteDTO(cliente);
    }
    
    // Método para transformar un Cliente a ClienteDTO
    public ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId_cliente(cliente.getId_cliente());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setTelefono(cliente.getTelefono());
        return clienteDTO;
    }
}
